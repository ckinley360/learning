
package stubs;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.ArrayList;

public class CardMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	ArrayList<String> suitsList = new ArrayList<>(); // To store the desired suits.
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		// Extract and process the suits parameter.
		String suits = context.getConfiguration().get("suits");
		String[] tempList = suits.split(",");
		
		for (String suit : tempList) {
			suitsList.add(suit);
		}
	}
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String inputLine = value.toString();
		
		String[] split = inputLine.split("\t");
		
		int cardValue = Integer.parseInt(split[0]);
		String cardSuit = split[1];
		
		// If the key is a desired suit, then write it.
		if (suitsList.contains(cardSuit.toLowerCase())) {
			context.write(new Text(cardSuit), new IntWritable(cardValue));
		}
	}
}
