package stubs;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MyKafkaConsumer {

	private KafkaConsumer<String, String> consumer;
	
	public void createConsumer() {
		String topic = "hello_topic";
		
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "broker1:9092");
		props.setProperty("group.id", "group1");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		// Create the consumer and subscribe to the topic
		consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList(topic));
		
		// Create a duration object to set the poll timeout
		Duration timeOut = Duration.ofMillis(100);
		
		while (true) {
			// Poll for ConsumerRecords for a certain amount of time
			ConsumerRecords<String, String> records = consumer.poll(timeOut);
			
			// Process the ConsumerRecords, if any, that come back
			for (ConsumerRecord<String, String> record : records) {
				//String key = record.key();
				String value = record.value();
				
				System.out.println(value);
			}
		}
	}
	
	public void close() {
		this.consumer.close();
	}
	
	public static void main(String[] args) {
		MyKafkaConsumer consumer = new MyKafkaConsumer();
		consumer.createConsumer();
		consumer.close();
	}
}
