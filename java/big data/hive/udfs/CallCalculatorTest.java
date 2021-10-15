package stubs;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

public class CallCalculatorTest {

	CallCalculator callCalculator;
	
	@Before
	public void setup() {
		callCalculator = new CallCalculator();
	}
	
	@Test
	public void testTimeDifference() {
		long output = callCalculator.evaluate(new Text(" 4/16/2021"), new IntWritable(133000), new IntWritable(144500)).get();
		
		assertEquals("Output does not match", 75, output);
	}
}
