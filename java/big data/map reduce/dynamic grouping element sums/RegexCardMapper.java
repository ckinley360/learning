
package stubs;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCardMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private static Pattern inputPattern = Pattern.compile("(.*)\\t(.*)\\t(.*)\\t(Spade|Club|Heart|Diamond)\\t(2|3|4|5|6|7|8|9|10)$");
	private static int groupingElementGroupNumber; // Stores the regex group number of the desired grouping element.
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		String groupingElement = context.getConfiguration().get("grouping element");
		
		if (groupingElement == "timestamp") {
			groupingElementGroupNumber = 1;
		} else if (groupingElement == "guid") {
			groupingElementGroupNumber = 2;
		} else if (groupingElement == "game") {
			groupingElementGroupNumber = 3;
		} else if (groupingElement == "suit") {
			groupingElementGroupNumber = 4;
		}
	}
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String inputLine = value.toString();
		Matcher inputMatch = inputPattern.matcher(inputLine);
		
		// If the input line matches the pattern we are looking for (non-face card), then write it.
		if (inputMatch.matches()) {
			String groupingElement = inputMatch.group(groupingElementGroupNumber);
			int cardValue = Integer.parseInt(inputMatch.group(5));
			context.write(new Text(groupingElement), new IntWritable(cardValue));
		}
	}
}
