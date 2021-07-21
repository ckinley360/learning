package stubs;

import org.apache.avro.Schema;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyValueInputFormat;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvroDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		String input, output;
		input = output = "";
		
		if (args.length == 2) {
			input = args[0];
			output = args[1];
		} else {
			System.err.println("Expected: input output");
		}
		
		/* Job 1: The ETL job (Map-only) */
		Job etlJob = Job.getInstance();
		etlJob.setJarByClass(AvroDriver.class);
		etlJob.setJobName("Card ETL");
		
		FileInputFormat.setInputPaths(etlJob, new Path(input));
		FileOutputFormat.setOutputPath(etlJob, new Path(output + "-etl"));
		
		etlJob.setInputFormatClass(TextInputFormat.class);
		etlJob.setOutputFormatClass(AvroKeyValueOutputFormat.class);
		
		etlJob.setMapperClass(RegexCardMapper.class);
		etlJob.setNumReduceTasks(0);
		
		AvroJob.setOutputKeySchema(etlJob, Card.getClassSchema());
		AvroJob.setOutputValueSchema(etlJob, Schema.create(Schema.Type.NULL));
		
		boolean success = etlJob.waitForCompletion(true);
		
		if (success == false) {
			System.err.println("First phase failed");
			return 1;
		}
		
		/* Job 2: The Map & Reduce */
		Job countJob = Job.getInstance();
		countJob.setJarByClass(AvroDriver.class);
		countJob.setJobName("Card Counter");
		
		FileInputFormat.setInputPaths(countJob, new Path(output + "-etl"));
		FileOutputFormat.setOutputPath(countJob, new Path(output));
		
		countJob.setInputFormatClass(AvroKeyValueInputFormat.class);
		countJob.setOutputFormatClass(AvroKeyValueOutputFormat.class);
		
		countJob.setMapperClass(CardMapper.class);
		countJob.setReducerClass(CardTotalReducer.class);
		
		countJob.setMapOutputKeyClass(Suit.class);
		countJob.setMapOutputValueClass(Card.class);
		
		AvroJob.setInputKeySchema(countJob, Card.getClassSchema());
		AvroJob.setInputValueSchema(countJob, Schema.create(Schema.Type.NULL));
		
		AvroJob.setMapOutputKeySchema(countJob, Suit.getClassSchema());
		AvroJob.setMapOutputValueSchema(countJob, Card.getClassSchema());
		
		AvroJob.setOutputKeySchema(countJob, Suit.getClassSchema());
		AvroJob.setOutputValueSchema(countJob, Schema.create(Schema.Type.INT));
		
		success = countJob.waitForCompletion(true);
		
		return success ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		AvroDriver driver = new AvroDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
