package stubs;

import org.apache.avro.mapreduce.AvroJob;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.parquet.avro.AvroParquetInputFormat;
import org.apache.parquet.avro.AvroParquetOutputFormat;

public class ParquetAvroDriver extends Configured implements Tool {

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
		
		/* Job 1: The ETL Job (Map-Only)*/
		// Create Job
		Job etlJob = Job.getInstance();
		etlJob.setJarByClass(ParquetAvroDriver.class);
		etlJob.setJobName("Card ETL");
		
		// Set File Input Path
		FileInputFormat.setInputPaths(etlJob, new Path(input));
		
		// Read the file in as regular text and save as Avro Parquet
		etlJob.setInputFormatClass(TextInputFormat.class);
		etlJob.setOutputFormatClass(AvroParquetOutputFormat.class);
		
		// Set Mapper class and specify that there is no Reducer
		etlJob.setMapperClass(RegexCardMapper.class);
		etlJob.setNumReduceTasks(0);
		
		// The Mapper outputs void as the key and the Card as the value
		etlJob.setOutputKeyClass(Void.class);
		etlJob.setOutputValueClass(Card.class);
		
		// Set the schema and location for the Parquet file(s)
		AvroParquetOutputFormat.setSchema(etlJob, Card.getClassSchema());
		AvroParquetOutputFormat.setOutputPath(etlJob, new Path(output + "-etl"));
		
		// Submit the job and wait for completion
		boolean success = etlJob.waitForCompletion(true);
		
		if (success == false) {
			System.err.println("First phase failed");
			return 1;
		}
		
		/* Job 2: The Suit Counting Job */
		Job countJob = Job.getInstance();
		countJob.setJarByClass(ParquetAvroDriver.class);
		countJob.setJobName("Count Job");
		
		// Set AvroParquet Input Path and File Output Path
		AvroParquetInputFormat.addInputPath(countJob, new Path(output + "-etl"));
		FileOutputFormat.setOutputPath(countJob, new Path(output));
		
		// Set the input as Parqet and output as regular text
		countJob.setInputFormatClass(AvroParquetInputFormat.class);
		countJob.setOutputFormatClass(TextOutputFormat.class);
		
		// Set the Mapper and Reducer Classes
		countJob.setMapperClass(CardMapper.class);
		countJob.setReducerClass(CardTotalReducer.class);
		
		// Set up the Avro schemas for the Mapper so they are read correctly by the Reducer
		AvroJob.setMapOutputKeySchema(countJob, Suit.getClassSchema());
		AvroJob.setMapOutputValueSchema(countJob, Card.getClassSchema());
		
		// Set the job output key and value classes
		countJob.setOutputKeyClass(Text.class);
		countJob.setOutputValueClass(Integer.class);
		
		// Create the projection for the cards stored in the file
		AvroParquetInputFormat.setRequestedProjection(countJob, Card.getClassSchema());
		
		// Submit the job and wait for completion
		success = countJob.waitForCompletion(true);
		
		return success ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		ParquetAvroDriver driver = new ParquetAvroDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
