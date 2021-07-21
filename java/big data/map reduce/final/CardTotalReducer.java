package stubs;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Reducer;

public class CardTotalReducer extends Reducer<AvroKey<Suit>, AvroValue<Card>, AvroKey<Suit>, AvroValue<Integer>> {
	
	@Override
	public void reduce(AvroKey<Suit> key, Iterable<AvroValue<Card>> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		
		// Go through all the values to sum up card values for a card suit.
		for (AvroValue<Card> value : values) {
			sum += Integer.parseInt(value.datum().getCard().toString());
		}
		
		// Emit the count grouped by suit.
		context.write(key, new AvroValue<Integer>(sum));
	}
}
