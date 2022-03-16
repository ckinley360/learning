package stubs;

import model.PoliceCall;

import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.types.avro.Avros;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CrunchAvroPolice extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new CrunchAvroPolice(), args);
	}
	
	public int run(String[] args) throws Exception {
		// Parse the args
		String input, output;
		
		if (args.length == 2) {
			input = args[0];
			output = args[1];
		} else {
			System.err.println("Expected: input output");
			return -1;
		}
		
		// Create a pipeline
		Pipeline pipeline = new MRPipeline(CrunchAvroPolice.class, getConf());
		
		// Read the data from the text file 
		PCollection<String> lines = pipeline.read(From.textFile(input));
		
		PCollection<PoliceCall> policeCalls = lines.parallelDo(new PoliceParsingDoFN(), Avros.specifics(PoliceCall.class));
		
		// Write the data to an Avro file
		policeCalls.write(To.avroFile(output));
		
		// Submit the job for execution
		PipelineResult result = pipeline.done();
		
		return result.succeeded() ? 0 : 1;
	}
}
