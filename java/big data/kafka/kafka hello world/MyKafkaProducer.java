package stubs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyKafkaProducer {

	public static void main(String[] args) {
		String inputFile = args[0];
		
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "broker1:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		
		// Read in data from the text file to create a ProducerRecord, then send it.
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			String[] parts = line.split("\t");
			
			String key = parts[0];
			String value = line;
			
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("hello_topic", key, value);
			producer.send(record);
			producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
