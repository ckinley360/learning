package stubs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class CardCount {

	public static void main(String[] args) {
		String inputFile = args[0];
		String output = args[1];
		
		// Create a Java Spark Context
		SparkConf conf = new SparkConf().setMaster("local").setAppName("Card Count");
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		// Load the input data.
		JavaRDD<String> input = sc.textFile(inputFile);
		
		// Split up into suits and numbers, then transform into pairs.
		JavaPairRDD<String, Integer> suitsAndValues = input.mapToPair(line -> {
			String[] pieces = line.split("\t");
			
			int cardValue = Integer.parseInt(pieces[0]);
			String cardSuit = pieces[1];
			
			return new Tuple2<String, Integer>(cardSuit, cardValue);
		});
		
		JavaPairRDD<String, Integer> counts = suitsAndValues.reduceByKey((x, y) -> x + y);
		
		counts.saveAsTextFile(output);
	}
}
