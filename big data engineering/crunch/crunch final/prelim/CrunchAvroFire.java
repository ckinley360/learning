package prelim;

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

import model.FireCall;

public class CrunchAvroFire extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new CrunchAvroFire(), args);
	}
	
	public int run(String[] args) throws Exception {
		// Parse the args
		String fireCallInput, output;
		
		if (args.length == 2) {
			fireCallInput = args[0];
			output = args[1];
		} else {
			System.err.println("Expected: fireCallInput output");
			return -1;
		}
		
		// Create a pipeline
		Pipeline pipeline = new MRPipeline(CrunchAvroFire.class, getConf());
		
		// Read the data from the text file
		PCollection<String> fireCallLines = pipeline.read(From.textFile(fireCallInput));
		
		// Parse the data and convert to FireCall objects
		PCollection<FireCall> fireCalls = fireCallLines.parallelDo(
				new FireCallParseDoFN(), 
				Avros.specifics(FireCall.class));
		
		// Write the data to an Avro file
		fireCalls.write(To.avroFile(output));
		
		// Submit the job for execution
		PipelineResult result = pipeline.done();
		
		return result.succeeded() ? 0 : 1;
	}
}
