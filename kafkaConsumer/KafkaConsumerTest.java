
//package kakfa;

import java.util.Properties;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.*;


public class KafkaConsumerTest {

	private static final String topicName = "conso-maisons";
	private static final String kafkaServerIP = "localhost:9092";

	public static void main(String[] args) {
	
		
	
		Properties props = new Properties();
		props.put("bootstrap.servers", kafkaServerIP);
		props.put("group.id", "groupName");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topicName));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}
	}

}
