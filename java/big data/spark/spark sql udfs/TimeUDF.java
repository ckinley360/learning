package stubs;

import java.sql.Timestamp;
import java.time.Duration;
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
			
			return Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, minute, second));
		}, DataTypes.TimestampType);
		
		// Output data using the timestampify UDF
		Dataset<Row> timeStampifiedData = sqlContext.sql("SELECT receive_date, receive_time, timestampify(receive_date, receive_time) FROM fire LIMIT 10");
		
		// Print out the timestampified data
		timeStampifiedData.javaRDD().collect().forEach((Row row) -> {
			System.out.println("Result: " + row.toString());
		});
		
		// Create a UDF to convert a date, time1, and time2 into two datetimes, then calculate the number of seconds between the two datetimes.
		sqlContext.udf().register("datetimediff", (String dateString, String timeOneString, String timeTwoString) -> {
			String[] dateParts = dateString.split("/");
			String[] timeOneParts = timeOneString.split(":");
			String[] timeTwoParts = timeTwoString.split(":");
			int year;
			int month;
			int day;
			int timeOneHour;
			int timeOneMinute;
			int timeOneSecond;
			int timeTwoHour;
			int timeTwoMinute;
			int timeTwoSecond;
			
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
			
			if (timeOneParts.length == 0) {
				timeOneHour = 0;
				timeOneMinute = 0;
				timeOneSecond = 0;
			} else {
				timeOneHour = Integer.valueOf(timeOneParts[0]);
				timeOneMinute = Integer.valueOf(timeOneParts[1]);
				timeOneSecond = Integer.valueOf(timeOneParts[2]);
			}
			
			if (timeTwoParts.length == 0) {
				timeTwoHour = 0;
				timeTwoMinute = 0;
				timeTwoSecond = 0;
			} else {
				timeTwoHour = Integer.valueOf(timeTwoParts[0]);
				timeTwoMinute = Integer.valueOf(timeTwoParts[1]);
				timeTwoSecond = Integer.valueOf(timeTwoParts[2]);
			}
			
			LocalDateTime dateTimeOne = LocalDateTime.of(year, month, day, timeOneHour, timeOneMinute, timeOneSecond);
			LocalDateTime dateTimeTwo = LocalDateTime.of(year, month, day, timeTwoHour, timeTwoMinute, timeTwoSecond);
			
			Duration duration = Duration.between(dateTimeOne, dateTimeTwo);
			
			return duration.getSeconds();
		}, DataTypes.LongType);
	}
}
