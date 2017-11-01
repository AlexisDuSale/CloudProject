
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.Durations;

public class SparkKafkaStreamLoader {
	public static void main(String[] args){
		// create a local Spark Context with two working threads
        SparkConf streamingConf = new SparkConf().setMaster("local[2]").setAppName("quarters_consomation");
        streamingConf.set("spark.streaming.stopGracefullyOnShutdown", "true");
 
        // create a Spark Java streaming context, with stream batch interval
        JavaStreamingContext jssc = new JavaStreamingContext(streamingConf, Durations.milliseconds(1000L));
        
        jssc.checkpoint(System.getProperty("java.io.tmpdir"));
        
        // set up receive data stream from kafka
        final Set topicsSet = new HashSet(Arrays.asList("conso-maisons"));
        final Map kafkaParams = new HashMap();
        kafkaParams.put("metadata.broker.list", "localhost:9092");
        
        // create a Discretized Stream of Java String objects
        // as a direct stream from kafka (zookeeper is not an intermediate)
        JavaPairInputDStream rawDataLines =
                KafkaUtils.createDirectStream(
                        jssc,
                        String.class,
                        String.class,
                        StringDecoder.class,
                        StringDecoder.class,
                        kafkaParams,
                        topicsSet
                );
	}
}
