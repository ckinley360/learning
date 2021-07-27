package stubs;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CardTotalReducer extends Reducer<AvroKey<Suit>, AvroValue<Card>, Text, IntWritable> {

	@Override
	public void reduce(AvroKey<Suit> key, Iterable<AvroValue<Card>> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		
		// Go through all values to sum up card values per suit.
		for (AvroValue<Card> value : values) {
			sum += Integer.parseInt(value.datum().getCard().toString());
		}
		
		// Emit the count per suit.
		context.write(new Text(key.datum().toString()), new IntWritable(sum));
	}
}
