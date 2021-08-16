package stubs;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.lib.Channels;
import org.apache.crunch.lib.Distinct;
import org.apache.crunch.lib.join.JoinType;
import org.apache.crunch.lib.join.MapsideJoinStrategy;
import org.apache.crunch.types.avro.Avros;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import model.PoliceCall;

public class AdvancedCrunchPolice extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new AdvancedCrunchPolice(), args);
	}
	
	public int run(String[] args) throws Exception {
		// Parse the args
		String callCostInput, callInput, output;
		
		if (args.length == 3) {
			callCostInput = args[0];
			callInput = args[1];
			output = args[2];
		} else {
			System.err.println("Expected: callCostInput callInput output");
			return -1;
		}
		
		// Create the pipelines for the call cost data and the call data
		Pipeline callCostPipeline = new MRPipeline(AdvancedCrunchPolice.class, getConf());
		Pipeline callPipeline = new MRPipeline(AdvancedCrunchPolice.class, getConf());
		
		// Read in the data from the source files
		PCollection<String> callCostLines = callCostPipeline.read(From.textFile(callCostInput));
		PCollection<PoliceCall> callLines = callPipeline.read(From.avroFile(callInput, Avros.records(PoliceCall.class)));
		
		// Parse the call cost and call data, then store in PTables
		PTable<Integer, Double> callCost = callCostLines.parallelDo(
				new PoliceCostParseDoFN(),
				Avros.tableOf(Avros.ints(), Avros.doubles()));
		PTable<Integer, PoliceCall> calls = callLines.parallelDo(
				new PolicePriorityParseDoFN(),
				Avros.tableOf(Avros.ints(), Avros.specifics(PoliceCall.class)));
		
		// ***JOIN***
		// Create the MapSideJoinStrategy
		MapsideJoinStrategy<Integer, Double, PoliceCall> mapSideStrategy = MapsideJoinStrategy.create();
		
		// Join the tables.
		PTable<Integer, Pair<Double, PoliceCall>> mapJoined = mapSideStrategy.join(callCost, calls, JoinType.INNER_JOIN);
		
		// ***DISTINCT***
		// Parse the jurisdictions and dispatch areas
		PCollection<Pair<String, String>> jurisdictionsAndDispatchAreas = callLines.parallelDo(
				new PoliceJurisdictionAndDispatchAreaParseDoFN(), 
				Writables.pairs(Writables.strings(), Writables.strings()));
		
		// Split the jurisdictions and dispatch areas into their own PCollections
		Pair<PCollection<String>, PCollection<String>> split = Channels.split(jurisdictionsAndDispatchAreas);
		PCollection<String> jurisdictions = split.first();
		PCollection<String> dispatchAreas = split.second();
		
		// Get the distinct jurisdictions and dispatch areas
		PCollection<String> distinctJurisdictions = Distinct.distinct(jurisdictions);
		PCollection<String> distinctDispatchAreas = Distinct.distinct(dispatchAreas);
		
		// Submit the job for execution
		PipelineResult result = callCostPipeline.done();
		
		return result.succeeded() ? 0 : 1;
	}
}