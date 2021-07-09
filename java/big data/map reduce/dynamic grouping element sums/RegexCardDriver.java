
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

public class RegexCardDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		String input, output;
		String groupingElement; // Will store the element that the user wants to group the card sums by.
		
		if (args.length == 2) {
			input = args[0];
			output = args[1];
			groupingElement = "suit"; // Default to grouping by suit.
		} else if (args.length > 2) {
			input = args[0];
			output = args[1];
			groupingElement = args[2].toLowerCase(); // Use the 3rd argument supplied as the grouping element. Ignore any additional arguments.
			// Ensure the grouping element supplied as an argument is valid.
			if (!(groupingElement.equals("timestamp") || groupingElement.equals("guid") || groupingElement.equals("game") || groupingElement.equals("suit"))) {
				throw new IllegalArgumentException("Grouping element must be either \"timestamp\", \"guid\", \"game\", or \"suit\".");
			}
		}
		else {
			System.err.println("Expected: input output [grouping element]");
			return -1;
		}
		
		Job job = Job.getInstance();
		job.setJarByClass(RegexCardDriver.class);
		job.setJobName("Card Counter");
		job.getConfiguration().set("grouping element", groupingElement);
		
		FileInputFormat.setInputPaths(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		job.setMapperClass(RegexCardMapper.class);
		job.setReducerClass(RegexCardTotalReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		RegexCardDriver driver = new RegexCardDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
