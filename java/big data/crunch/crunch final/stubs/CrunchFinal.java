package stubs;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.lib.join.JoinType;
import org.apache.crunch.lib.join.MapsideJoinStrategy;
import org.apache.crunch.types.avro.Avros;
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
		
		return 1;
	}
}
