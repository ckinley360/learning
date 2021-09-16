package stubs;

import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.PCollection;
import org.apache.crunch.PGroupedTable;
import org.apache.crunch.PTable;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.lib.SecondarySort;
import org.apache.crunch.lib.join.JoinType;
import org.apache.crunch.lib.join.MapsideJoinStrategy;
import org.apache.crunch.types.avro.Avros;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import model.FireCall;
import model.PoliceCall;

public class CrunchFinal extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new CrunchFinal(), args);
	}
	
	public int run(String[] args) throws Exception {
		// Parse the args
		String callCostInput, policeCallInput, fireCallInput, output;
		
		if (args.length == 4) {
			callCostInput = args[0];
			policeCallInput = args[1];
			fireCallInput = args[2];
			output = args[3];
		} else {
			System.err.println("Expected: callCostInput policeCallInput fireCallInput output");
			return -1;
		}
		
		// Create the pipeline
		Pipeline pipeline = new MRPipeline(CrunchFinal.class, getConf());
		
		// Read in the data from the source files
		PCollection<String> callCostLines = pipeline.read(From.textFile(callCostInput));
		PCollection<PoliceCall> policeCallLines = pipeline.read(From.avroFile(policeCallInput, PoliceCall.class));
		PCollection<FireCall> fireCallLines = pipeline.read(From.avroFile(fireCallInput, FireCall.class));
		
		// Parse the call cost, police call, and fire call data, then store in PTables
		// <Priority, Cost>
		PTable<Integer, Double> callCost = callCostLines.parallelDo(
				new CallCostParseDoFN(), 
				Avros.tableOf(Avros.ints(), Avros.doubles()));
		// <Priority, PoliceCall>
		PTable<Integer, PoliceCall> policeCalls = policeCallLines.parallelDo(
				new PolicePriorityParseDoFN(), 
				Avros.tableOf(Avros.ints(), Avros.specifics(PoliceCall.class)));
		// <Priority, FireCall>
		PTable<Integer, FireCall> fireCalls = fireCallLines.parallelDo(
				new FirePriorityParseDoFN(),
				Avros.tableOf(Avros.ints(), Avros.specifics(FireCall.class)));
		
		// ***JOINS***
		// Create the MapSideJoinStrategy objects
		MapsideJoinStrategy<Integer, Double, PoliceCall> policeMapSideStrategy = MapsideJoinStrategy.create();
		MapsideJoinStrategy<Integer, Double, FireCall> fireMapSideStrategy = MapsideJoinStrategy.create();
		
		// Join the tables
		// <Priority, <Cost, PoliceCall>>
		PTable<Integer, Pair<Double, PoliceCall>> policeMapJoined = policeMapSideStrategy.join(callCost, policeCalls, JoinType.INNER_JOIN);
		// <Priority, <Cost, FireCall>>
		PTable<Integer, Pair<Double, FireCall>> fireMapJoined = fireMapSideStrategy.join(callCost, fireCalls, JoinType.INNER_JOIN);
		
		// ***GROUPING***
		// First, switch the key & value of the policeMapJoined & fireMapJoined PTables from <Priority, <Cost, PoliceCall>> & <Priority, <Cost, FireCall>> to 
		// <Dispatch Date, Cost>
		PTable<String, Double> policeDateToCost = policeMapJoined.parallelDo(
				new PoliceParseDateAndCostDoFN(),
				Writables.tableOf(Writables.strings(), Writables.doubles()));
		PTable<String, Double> fireDateToCost = fireMapJoined.parallelDo(
				new FireParseDateAndCostDoFN(),
				Writables.tableOf(Writables.strings(), Writables.doubles())); 
		
		// Next, group the costs together by the day of the call
		PGroupedTable<String, Double> policeGroupedByDateToCost = policeDateToCost.groupByKey();
		PGroupedTable<String, Double> fireGroupedByDateToCost = fireDateToCost.groupByKey();
		
		// Then, sum the group values to get the cost per day. Each row is unique - date, cost.
		PTable<String, Double> policeCostPerDay = policeGroupedByDateToCost.combineValues(Aggregators.SUM_DOUBLES());
		PTable<String, Double> fireCostPerDay = fireGroupedByDateToCost.combineValues(Aggregators.SUM_DOUBLES());
		
		// Write the costs per day to two text files
//		policeCostPerDay.write(To.textFile(output + "_police"));
//		fireCostPerDay.write(To.textFile(output + "_fire"));
		
		// Prepare the datasets for a secondary sort by making the key the call type ("police" or "fire"), and the value a pair of date & cost.
		PTable<String, Pair<String, Double>> secondarySortablePoliceCostPerDay = policeCostPerDay.parallelDo(
				new makePoliceCostPerDaySecondarySortableDoFN(),
				Writables.tableOf(Writables.strings(), Writables.pairs(Writables.strings(), Writables.doubles())));
		PTable<String, Pair<String, Double>> secondarySortableFireCostPerDay = fireCostPerDay.parallelDo(
				new makeFireCostPerDaySecondarySortableDoFN(),
				Writables.tableOf(Writables.strings(), Writables.pairs(Writables.strings(), Writables.doubles())));
		
		// Run a secondary sort on the costs per day to calculate a running average for each dataset
		PTable<String, Pair<String, Double>> policeCallCostRunningAverage = SecondarySort.sortAndApply(
				secondarySortablePoliceCostPerDay,
				new SecondarySortRunningAverageDoFN(),
				Writables.tableOf(Writables.strings(), Writables.pairs(Writables.strings(), Writables.doubles())));
		PTable<String, Pair<String, Double>> fireCallCostRunningAverage = SecondarySort.sortAndApply(
				secondarySortableFireCostPerDay,
				new SecondarySortRunningAverageDoFN(),
				Writables.tableOf(Writables.strings(), Writables.pairs(Writables.strings(), Writables.doubles())));
		
		// Write the cost running averages to two text files
		policeCallCostRunningAverage.write(To.textFile(output + "_police"));
		fireCallCostRunningAverage.write(To.textFile(output + "_fire"));
		
		// Submit the job for execution
		PipelineResult result = pipeline.done();
		
		return result.succeeded() ? 0 : 1;
	}
}
