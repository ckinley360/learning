package stubs;

import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CrunchAvroPolice extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new CrunchAvroPolice(), args);
	}
	
	public int run(String[] args) throws Exception {
		// Create a pipeline
		Pipeline pipeline = new MRPipeline(CrunchAvroPolice.class, getConf());
		
		// Read the data from the text file 
		PCollection<String> lines = pipeline.readTextFile(args[0]);
		
		// Write the data to an Avro file
		lines.write(To.avroFile(args[1]));
		
		// Submit the job for execution
		PipelineResult result = pipeline.done();
		
		return result.succeeded() ? 0 : 1;
	}
}
