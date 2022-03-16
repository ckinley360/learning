package stubs;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner extends Partitioner<AvroKey<Suit>, AvroValue<Integer>> {

	@Override
	public int getPartition(AvroKey<Suit> key, AvroValue<Integer> value, int numPartitions) {
		String suit = key.datum().toString().toLowerCase();
		
		// Each suit is evenly distributed, so try to evenly distribute each suit to its own partition.
		if (numPartitions == 0 || numPartitions == 1) {
			return 0;
		} else if (numPartitions == 2) { 
			if (suit.equals("club") || suit.equals("spade")) {
				return 0;
			} else {
				return 1;
			}
		} else if (numPartitions == 3) {
			if (suit.equals("club") || suit.equals("spade")) {
				return 0;
			} else if (suit.equals("diamond")) {
				return 1;
			} else {
				return 2;
			}
		} else {
			if (suit.equals("club")) {
				return 0;
			} else if (suit.equals("spade")) {
				return 1;
			} else if (suit.equals("diamond")) {
				return 2;
			} else {
				return 3;
			}
		}
	}
}
