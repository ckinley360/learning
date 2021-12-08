package stubs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyKafkaProducer {
	
	public void createProducer(String inputFile) throws FileNotFoundException {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "broker1:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		
		// Read in data from the text file to create a ProducerRecord, then send it.
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line = null;
		
		try {
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\t");
				
				String key = parts[0];
				String value = line;
				
				ProducerRecord<String, String> record = new ProducerRecord<String, String>("hello_topic", key, value);
				producer.send(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				producer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}
	
	public static void main(String[] args) {
		String inputFile = args[0];
		MyKafkaProducer producer = new MyKafkaProducer();
		
		try {
			producer.createProducer(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
