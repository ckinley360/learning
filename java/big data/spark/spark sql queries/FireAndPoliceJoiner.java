package stubs;

import java.util.Arrays;

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

public class FireAndPoliceJoiner {

	public static void main(String[] args) {
		// Receive the arguments
		String fireCallsInput = args[0];
		String policeCallsInput = args[1];
		
		// Create the configuration and context objects
		SparkConf conf = new SparkConf();
		
		JavaSparkContext sc = new JavaSparkContext("local", "Fire And Police", conf);
		SQLContext sqlContext = new SQLContext(sc);
		
		// Read in the fire data
		Dataset<Row> fireDataFrame = sqlContext.read().parquet(fireCallsInput);
		
		// Create the fire temp table
		fireDataFrame.registerTempTable("fire");
		
		// Read in the police data
		JavaRDD<String> lines = sc.textFile(policeCallsInput);
		JavaRDD<Row> rows = lines.map((String line) -> {
			String[] parts = line.split(",");
			
			// If the array is length 9 due to missing data, then fill the last element with an empty string
			if (parts.length == 9) {
				String[] extendedParts = Arrays.copyOf(parts, parts.length + 1);
				extendedParts[9] = "";
				return RowFactory.create(extendedParts[0], extendedParts[1], extendedParts[2], extendedParts[3], extendedParts[4].trim(), extendedParts[5], extendedParts[6], extendedParts[7], extendedParts[8], extendedParts[9]);
			}
			
			// Priority, Call_Type, Jurisdiction, Dispatch_Area, Received_Date, Received_Time, Dispatch_Time, Arrival_Time, Cleared_Time, Disposition
			// 1, EMSP, RP, RC, 03/21/2013, 095454, 000000, 000000, 101659, CANC
			return RowFactory.create(parts[0], parts[1], parts[2], parts[3], parts[4].trim(), parts[5], parts[6], parts[7], parts[8], parts[9]);
		});
		
		// Create the police data frame
		StructField priority = DataTypes.createStructField("priority", DataTypes.StringType, false);
		StructField callType = DataTypes.createStructField("call_type", DataTypes.StringType, false);
		StructField jurisdiction = DataTypes.createStructField("jurisdiction", DataTypes.StringType, false);
		StructField dispatchArea = DataTypes.createStructField("dispatch_area", DataTypes.StringType, false);
		StructField receiveDate = DataTypes.createStructField("receive_date", DataTypes.StringType, false);
		StructField receiveTime = DataTypes.createStructField("receive_time", DataTypes.StringType, false);
		StructField dispatchTime = DataTypes.createStructField("dispatch_time", DataTypes.StringType, false);
		StructField arrivalTime = DataTypes.createStructField("arrival_time", DataTypes.StringType, false);
		StructField clearTime = DataTypes.createStructField("clear_time", DataTypes.StringType, false);
		StructField disposition = DataTypes.createStructField("disposition", DataTypes.StringType, false);
		
		StructType schema = DataTypes.createStructType(
				new StructField[] { priority, callType, jurisdiction, dispatchArea, receiveDate, receiveTime, dispatchTime, arrivalTime, clearTime, disposition });
		
		Dataset<Row> policeDataFrame = sqlContext.createDataFrame(rows, schema);
		
		// Create the police temp table
		policeDataFrame.registerTempTable("police");
		
        // Union the fire and police data
		//Dataset<Row> unionedData = sqlContext.sql("SELECT 'fire' AS agency, receive_date FROM fire UNION SELECT 'police' AS agency, receive_date FROM police LIMIT 10");
		Dataset<Row> combinedCallCountByDate = sqlContext.sql("WITH unionedData AS (\n" + 
				"    SELECT receive_date\n" + 
				"    FROM fire\n" + 
				"\n" + 
				"    UNION ALL\n" + 
				"    \n" + 
				"    SELECT receive_date \n" + 
				"    FROM police\n" + 
				")\n" + 
				"\n" + 
				"SELECT receive_date,\n" + 
				"       COUNT(*) AS count_of_calls\n" + 
				"FROM unionedData\n" + 
				"GROUP BY receive_date\n" + 
				"ORDER BY COUNT(*) DESC");
		
		combinedCallCountByDate.javaRDD().collect().forEach((Row row) -> {
			System.out.println("Result:" + row.toString());
		});
	}
}
