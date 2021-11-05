package stubs;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;

public class TimeUDF {

	public static void main(String[] args) {
		// Receive the argument
		String fireCallsInput = args[0];
		
		// Create the configuration and context objects
		SparkConf conf = new SparkConf();
		
		JavaSparkContext sc = new JavaSparkContext("local", "Fire Calls", conf);
		SQLContext sqlContext = new SQLContext(sc);
		
		// Read in the fire data
		Dataset<Row> fireDataFrame = sqlContext.read().parquet(fireCallsInput);
		
		// Create the fire temp table
		// ALARM_LEVEL, CALL_TYPE, JURISDICTION, STATION, RECEIVED_DATE, RECEIVED_TIME, DISPATCH_1ST_TIME, ONSCENE_1ST_TIME, FIRE_CONTROL_TIME, CLOSE_TIME
		// 1,BRUSH1,RF,15,01/01/2012,00:33:04,00:36:00,00:41:00,,00:54:13
		fireDataFrame.registerTempTable("fire");
		
		// Print out the data
//		Dataset<Row> testData = sqlContext.sql("SELECT * FROM fire LIMIT 10");
//		testData.javaRDD().collect().forEach((Row row) -> {
//			System.out.println("Result: " + row.toString());
//		});
		
		// Create a UDF to combine a date and time into a datetime
		sqlContext.udf().register("timestampify", (String dateString, String timeString) -> {
			String[] dateParts = dateString.split("/");
			String[] timeParts = timeString.split(":");
			int year;
			int month;
			int day;
			int hour;
			int minute;
			int second;
			
			// If the date is an empty string, use the Unix epoch (1/1/1970). If the time is an empty string, use midnight (00:00:00).
			if (dateParts.length == 0) {
				year = 1970;
				month = 1;
				day = 1;
			} else {
				year = Integer.valueOf(dateParts[2]);
				month = Integer.valueOf(dateParts[0]);
				day = Integer.valueOf(dateParts[1]);
			}
			
			if (timeParts.length == 0) {
				hour = 0;
				minute = 0;
				second = 0;
			} else {
				hour = Integer.valueOf(timeParts[0]);
				minute = Integer.valueOf(timeParts[1]);
				second = Integer.valueOf(timeParts[2]);
			}
			
			return Timestamp.valueOf(LocalDateTime.of(Integer.valueOf(dateParts[2]), Integer.valueOf(dateParts[0]), Integer.valueOf(dateParts[1]), Integer.valueOf(timeParts[0]), Integer.valueOf(timeParts[1]), Integer.valueOf(timeParts[2])));
		}, DataTypes.TimestampType);
		
		// Output data using the timestampify UDF
		Dataset<Row> timeStampifiedData = sqlContext.sql("SELECT receive_date, receive_time, timestampify(receive_date, receive_time) FROM fire LIMIT 10");
		
		// Print out the timestampified data
		timeStampifiedData.javaRDD().collect().forEach((Row row) -> {
			System.out.println("Result: " + row.toString());
		});
	}
}
