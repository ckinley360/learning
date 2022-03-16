package stubs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class RegexCardTest {

	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	
	@Before
	public void setUp() {
		RegexCardMapper mapper = new RegexCardMapper();
		mapDriver = MapDriver.newMapDriver(mapper);
		
		RegexCardTotalReducer reducer = new RegexCardTotalReducer();
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		
		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}
	
	@Test
	public void testMapper() throws IOException {
		mapDriver
		.withInput(new LongWritable(), new Text("2015-01-10 00:00:07\t16e57c25-4f82-46e5-bf26-8fa0796d8588\tBlackjack\tDiamond\t5"));
		mapDriver
		.withOutput(new Text("Blackjack"), new IntWritable(5));
		mapDriver.runTest();
	}
	
	@Test
	public void testReducer() throws IOException {
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(7));
		values.add(new IntWritable(6));
		reduceDriver.withInput(new Text("Spades"), values);
		reduceDriver.withOutput(new Text("Spades"), new IntWritable(13));
		reduceDriver.runTest();
	}
	
	@Test
	public void testMapReduce() throws IOException {
		mapReduceDriver
		.withInput(new LongWritable(), new Text("2015-01-10 00:00:07\t16e57c25-4f82-46e5-bf26-8fa0796d8588\tBlackjack\tDiamond\t5"))
		.withInput(new LongWritable(), new Text("2015-01-10 00:00:07\t16e57c25-4f82-46e5-bf26-8fa0796d8588\tBlackjack\tHeart\t3"))
		.withInput(new LongWritable(), new Text("2015-01-10 00:00:07\t16e57c25-4f82-46e5-bf26-8fa0796d8588\tTexas Holdem\tSpade\t10"))
		.withInput(new LongWritable(), new Text("2015-01-10 00:00:07\t16e57c25-4f82-46e5-bf26-8fa0796d8588\tTexas Holdem\tSpade\t4"));
		
		// withOutput keys must be in order!
		mapReduceDriver
		.withOutput(new Text("Blackjack"), new IntWritable(8))
		.withOutput(new Text("Texas Holdem"), new IntWritable(14));
		
		mapReduceDriver.runTest();
	}
}
