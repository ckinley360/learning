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
		// Create Job
		Job etlJob = Job.getInstance();
		etlJob.setJarByClass(AvroDriver.class);
		etlJob.setJobName("Card ETL");
		
		// Set File Input and Output Paths
		FileInputFormat.setInputPaths(etlJob, new Path(input));
		FileOutputFormat.setOutputPath(etlJob, new Path(output + "-etl"));
		
		// Set Input and Output Format for Data
		etlJob.setInputFormatClass(TextInputFormat.class);
		etlJob.setOutputFormatClass(AvroKeyValueOutputFormat.class);
		
		// Set Mapper Class and Specify that There is No Reducer
		etlJob.setMapperClass(RegexCardMapper.class);
		etlJob.setNumReduceTasks(0);
		
		// Set Avro Output Key and Value Schemas
		AvroJob.setOutputKeySchema(etlJob, Card.getClassSchema());
		AvroJob.setOutputValueSchema(etlJob, Schema.create(Schema.Type.NULL));
		
		// Submit the job and wait for completion.
		boolean success = etlJob.waitForCompletion(true);
		
		// Print out the count of each suit by using the "suits" counter.
		long numberOfSpades = etlJob.getCounters().getGroup("suits").findCounter("spade").getValue();
		long numberOfClubs = etlJob.getCounters().getGroup("suits").findCounter("club").getValue();
		long numberOfHearts = etlJob.getCounters().getGroup("suits").findCounter("heart").getValue();
		long numberOfDiamonds = etlJob.getCounters().getGroup("suits").findCounter("diamond").getValue();
		System.out.println("Spades: " + numberOfSpades);
		System.out.println("Clubs: " + numberOfClubs);
		System.out.println("Hearts: " + numberOfHearts);
		System.out.println("Diamonds: " + numberOfDiamonds);
		
		if (success == false) {
			System.err.println("First phase failed");
			return 1;
		}
		
		/* Job 2: The Map & Reduce */
		// Create Job
		Job countJob = Job.getInstance();
		countJob.setJarByClass(AvroDriver.class);
		countJob.setJobName("Card Counter");
		
		// Set File Input and Output Paths
		FileInputFormat.setInputPaths(countJob, new Path(output + "-etl"));
		FileOutputFormat.setOutputPath(countJob, new Path(output));
		
		// Set Input and Output Format for Data
		countJob.setInputFormatClass(AvroKeyValueInputFormat.class);
		countJob.setOutputFormatClass(AvroKeyValueOutputFormat.class);
		
		// Set Mapper, Combiner, and Reducer Classes
		countJob.setMapperClass(CardMapper.class);
		countJob.setCombinerClass(CardTotalReducer.class);
		countJob.setReducerClass(CardTotalReducer.class);
		
		// Set the Custom Partitioner Class
		countJob.setPartitionerClass(CustomPartitioner.class);
		
		// Set Avro Input Key and Value Schemas
		AvroJob.setInputKeySchema(countJob, Card.getClassSchema());
		AvroJob.setInputValueSchema(countJob, Schema.create(Schema.Type.NULL));
		
		// Set Avro Map Output Key and Value Schemas
		AvroJob.setMapOutputKeySchema(countJob, Suit.getClassSchema());
		AvroJob.setMapOutputValueSchema(countJob, Schema.create(Schema.Type.INT));
		
		// Set Avro Output Key and Value Schemas
		AvroJob.setOutputKeySchema(countJob, Suit.getClassSchema());
		AvroJob.setOutputValueSchema(countJob, Schema.create(Schema.Type.INT));
		
		// Submit the job and wait for completion.
		success = countJob.waitForCompletion(true);
		
		return success ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		AvroDriver driver = new AvroDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
