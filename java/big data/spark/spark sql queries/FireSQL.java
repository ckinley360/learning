package stubs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class FireSQL {

	public static void main(String[] args) {
		// Receive the arguments
		String input = args[0];
		String output = args[1];
		
		// Create the configuration and context objects
		SparkConf conf = new SparkConf();
		
		JavaSparkContext sc = new JavaSparkContext("local", "Fire Queries", conf);
		SQLContext sqlContext = new SQLContext(sc);
		
		// Read in the data
		JavaRDD<String> lines = sc.textFile(input);
		JavaRDD<Row> rows = lines.map((String line) -> {
			String[] parts = line.split(",");
			
			// ALARM_LEVEL, CALL_TYPE, JURISDICTION, STATION, RECEIVED_DATE, RECEIVED_TIME, DISPATCH_1ST_TIME, ONSCENE_1ST_TIME, FIRE_CONTROL_TIME, CLOSE_TIME
			// "1", "EMS", "RF", "01", "1/1/2012", "00:00:52", "00:01:11", "00:01:44", "", "00:08:11"
			return RowFactory.create(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9]);
		});
		
		// Create the dataframe
		StructField alarmLevel = DataTypes.createStructField("alarm_level", DataTypes.StringType, false);
		StructField callType = DataTypes.createStructField("call_type", DataTypes.StringType, false);
		StructField jurisdiction = DataTypes.createStructField("jurisdiction", DataTypes.StringType, false);
		StructField station = DataTypes.createStructField("station", DataTypes.StringType, false);
		StructField receiveDate = DataTypes.createStructField("receive_date", DataTypes.StringType, false);
		StructField receiveTime = DataTypes.createStructField("receive_time", DataTypes.StringType, false);
		StructField dispatchTime = DataTypes.createStructField("dispatch_time", DataTypes.StringType, false);
		StructField arrivalTime = DataTypes.createStructField("arrival_time", DataTypes.StringType, false);
		StructField fireControlTime = DataTypes.createStructField("fire_control_time", DataTypes.StringType, false);
		StructField closeTime = DataTypes.createStructField("close_time", DataTypes.StringType, false);
		
		StructType schema = DataTypes.createStructType(
				new StructField[] { alarmLevel, callType, jurisdiction, station, receiveDate, receiveTime, dispatchTime, arrivalTime, fireControlTime, closeTime });
		
		Dataset<Row> dataFrame = sqlContext.createDataFrame(rows, schema);
		
		// Create the temp table
		dataFrame.registerTempTable("firecalls");
		
		// Run queries on the temp table
		Dataset<Row> alarmLevelOne = sqlContext.sql("SELECT * FROM firecalls WHERE alarm_level = '1'");
		Dataset<Row> janThird = sqlContext.sql("SELECT * FROM firecalls WHERE receive_date = '1/3/2013'");
		Dataset<Row> callCountByCallType = sqlContext.sql("SELECT call_type, COUNT(*) AS count_of_calls FROM firecalls GROUP BY call_type ORDER BY COUNT(*) DESC");
		
		// Output the results of the 3rd query to the console
//		callCountByCallType.javaRDD().collect().forEach((Row row) -> {
//			System.out.println("Result:" + row.toString());
//		});
		
		// Save the firecalls dataframe to disk
		dataFrame.save("/home/vmuser/training/datasets/firecalls");
	}
}
