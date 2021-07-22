package stubs;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Reducer;

public class CardTotalReducer extends Reducer<AvroKey<Suit>, AvroValue<Integer>, AvroKey<Suit>, AvroValue<Integer>> {
	
	@Override
	public void reduce(AvroKey<Suit> key, Iterable<AvroValue<Integer>> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		
		// Go through all the values to count the number of cards for each suit.
		for (AvroValue<Integer> value : values) {
			count += value.datum();
		}
		
		// Emit the count grouped by suit.
		context.write(key, new AvroValue<Integer>(count));
	}
}
