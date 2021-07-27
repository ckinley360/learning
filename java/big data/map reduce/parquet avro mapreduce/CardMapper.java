package stubs;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Mapper;

public class CardMapper extends Mapper<Void, Card, AvroKey<Suit>, AvroValue<Card>> {

	@Override
	public void map(Void key, Card value, Context context) throws IOException, InterruptedException {
		// Emit and group based on suit.
		context.write(new AvroKey<Suit>(value.getSuit()), new AvroValue<Card>(value));
	}
}
