
package stubs;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCardMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private static Pattern inputPattern = Pattern.compile("(.*)\\t(.*)\\t(Spade|Club|Heart|Diamond)\\t(2|3|4|5|6|7|8|9|10)$");
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String inputLine = value.toString();
		Matcher inputMatch = inputPattern.matcher(inputLine);
		
		// If the input line matches the pattern we are looking for (non-face card), then write it.
		if (inputMatch.matches()) {
			String game = inputMatch.group(2);
			int cardValue = Integer.parseInt(inputMatch.group(4));
			context.write(new Text(game), new IntWritable(cardValue));
		}
	}
}
