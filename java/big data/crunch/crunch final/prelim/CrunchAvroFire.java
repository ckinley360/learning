package prelim;

import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.From;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

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
		
		
	}
}
