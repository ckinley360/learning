
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
		
		if (args.length == 2) {
			input = args[0];
			output = args[1];
		} else {
			System.err.println("Expected: input output");
			return -1;
		}
		
		Job job = Job.getInstance();
		job.setJarByClass(RegexCardDriver.class);
		job.setJobName("Card Counter");
		
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
