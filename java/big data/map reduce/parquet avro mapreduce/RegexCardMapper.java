package stubs;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RegexCardMapper extends Mapper<LongWritable, Text, Void, Card> {

	/**
	 * Example line:<br>
	 * 2015-01-10 00:00:07	16e57c25-4f82-46e5-bf26-8fa0796d8588	Blackjack	Diamond	8
	 */
	private static Pattern inputPattern = Pattern.compile("(.*)\\t(.*)\\t(.*)\\t(Spade|Club|Heart|Diamond)\\t(2|3|4|5|6|7|8|9|10)$");
	private static final Card.Builder cardBuilder = Card.newBuilder();
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String inputLine = value.toString();
		Matcher inputMatch = inputPattern.matcher(inputLine);
		
		// If the input line matches the pattern we are looking for (non-face card), then write it. Otherwise, ignore it.
		if (inputMatch.matches()) {
			String startTime = inputMatch.group(1);
			String gameId = inputMatch.group(2);
			GameType gameType = GameType.valueOf(inputMatch.group(3).toUpperCase());
			Suit suit = Suit.valueOf(inputMatch.group(4).toUpperCase());
			String cardNumber = inputMatch.group(5);
			
			Card card = cardBuilder.build();
			card.setStartTime(startTime);
			card.setGameId(gameId);
			card.setGameType(gameType);
			card.setSuit(suit);
			card.setCard(cardNumber);
			
			// Emit the data to the file system. Key is void because Parquet MR only saves the value to the file.
			context.write(null, card);
		}
	}
}
