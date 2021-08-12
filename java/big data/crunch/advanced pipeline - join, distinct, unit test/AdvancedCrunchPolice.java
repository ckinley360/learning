package stubs;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pipeline;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

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
		
		// Create a pipeline for the call cost data
		Pipeline callCostPipeline = new MRPipeline(AdvancedCrunchPolice.class, getConf());
		
		// Read in the call cost data from the text file
		PCollection<String> callCostLines = callCostPipeline.read(From.textFile(callCostInput));
		
		// Parse the call cost data and store in a PTable
		PTable<Integer, Double> callCost = callCostLines.parallelDo(
				new PoliceCostParseDoFN(),
				Writables.tableOf(Writables.ints(), Writables.doubles()));
	}
}
