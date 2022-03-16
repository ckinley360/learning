
package stubs;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.lang.StringBuilder;

public class CardDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		String input, output;
		// Create a string to store the comma-separated suits for which user wants to output the sum.
		StringBuilder suits = new StringBuilder();
		
		if (args.length >= 2) {
			input = args[0];
			output = args[1];
			
			// Default to including all suits unless otherwise specified by user.
			suits.append("club,spade,heart,diamond");
			
			// If user specified which suits to show, then store those suits in the string.  We will process the string when we create the Job.
			if (args.length > 2) {
				
				suits.setLength(0); // Empty out the string.
				boolean firstParameter = true; // The first parameter will not be preceded by a comma, and this will track whether we are looping over the first parameter.
				
				for (int i = 2; i <= args.length - 1; i++) {
					
					if (firstParameter) {
						suits.append(args[i].toLowerCase());
						firstParameter = false;
					} else {
						suits.append("," + args[i].toLowerCase());
					}
					
				}
			}
		
		} else {
			System.err.println("Expected: input output");
			return -1;
		}
		
		Job job = Job.getInstance();
		job.setJarByClass(CardDriver.class);
		job.setJobName("Card Counter");
		job.getConfiguration().set("suits", suits.toString()); // Set the suits parameter to specify which suits for which to output the sum. Defaults is all 4 suits.
		
		FileInputFormat.setInputPaths(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		job.setMapperClass(CardMapper.class);
		job.setReducerClass(CardTotalReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		CardDriver driver = new CardDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
