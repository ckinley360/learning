package stubs;

import java.io.File;
import java.sql.Date;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class sparkfinal {

	// Create the configuration and context objects
	public static SparkConf conf = new SparkConf();
	public static JavaSparkContext sc = new JavaSparkContext("local", "Spark Final", conf);
	//SQLContext sqlContext = new SQLContext(sc);
	public static SparkSession sparkSession = SparkSession.builder().master("local").appName("Spark Final").config(conf).getOrCreate();
	
	public static void main(String[] args) {
		// Receive the filepath arguments and create File objects with them 
		File alkFile = new File(args[0]);
//		File amznFile = new File(args[1]);
//		File atviFile = new File(args[2]);
//		File coinFile = new File(args[3]);
//		File dbxFile = new File(args[4]);
//		File docuFile = new File(args[5]);
//		File googFile = new File(args[6]);
//		File googlFile = new File(args[7]);
//		File jnjFile = new File(args[8]);
//		File jpmFile = new File(args[9]);
//		File msftFile = new File(args[10]);
//		File nflxFile = new File(args[11]);
//		File nvdaFile = new File(args[12]);
//		File psxFile = new File(args[13]);
//		File pyplFile = new File(args[14]);
//		File qcomFile = new File(args[15]);
//		File rdfnFile = new File(args[16]);
//		File rostFile = new File(args[17]);
//		File sbuxFile = new File(args[18]);
//		File sqFile = new File(args[19]);
		
		// Read in the data
		JavaRDD<Row> alkRows = readNasdaqData(sc.textFile(alkFile.getPath()), alkFile.getName().replace(".csv", ""));
//		JavaRDD<Row> amznRows = amznInput;
//		JavaRDD<Row> atviRows = atviInput;
//		JavaRDD<Row> coinRows = coinInput;
//		JavaRDD<Row> dbxRows = dbxInput;
//		JavaRDD<Row> docuRows = docuInput;
//		JavaRDD<Row> googRows = googInput;
//		JavaRDD<Row> googlRows = googlInput;
//		JavaRDD<Row> jnjRows = jnjInput;
//		JavaRDD<Row> jpmRows = jpmInput;
//		JavaRDD<Row> msftRows = msftInput;
//		JavaRDD<Row> nflxRows = nflxInput;
//		JavaRDD<Row> nvdaRows = nvdaInput;
//		JavaRDD<Row> psxRows = psxInput;
//		JavaRDD<Row> pyplRows = pyplInput;
//		JavaRDD<Row> qcomRows = qcomInput;
//		JavaRDD<Row> rdfnRows = rdfnInput;
//		JavaRDD<Row> rostRows = rostInput;
//		JavaRDD<Row> sbuxRows = sbuxInput;
//		JavaRDD<Row> sqRows = sqInput;
		
		// Create a dataframe for each stock dataset
		Dataset<Row> alkDataFrame = createDataFrame(alkRows);
		
		alkDataFrame.javaRDD().collect().forEach((Row row) -> {
			System.out.println("Result:" + row.toString());
		});
	}
	
	// Read in data sourced from https://www.nasdaq.com/market-activity/stocks
	// Date,Close/Last,Volume,Open,High,Low
	// 11/08/2021,$150.44,55020870,$151.41,$151.57,$150.16
	public static JavaRDD<Row> readNasdaqData(JavaRDD<String> lines, String symbol) {
		// Delete the header row
		String headerRow = lines.first();
		JavaRDD<String> headerlessLines = lines.filter((String line) -> !line.equals(headerRow));
		
		JavaRDD<Row> rows = headerlessLines.map((String line) -> {
			String[] parts = line.split(",");
			
			String dateString = parts[0];
			float closePrice = Float.parseFloat(parts[1].replace("$", "")); // Remove the $ from close price and convert to float
			float openPrice = Float.parseFloat(parts[3].replace("$", "")); // Remove the $ from open price and convert to float
			
			// Convert the date string to yyyy-mm-dd so we can create a Date object
			String[] dateParts = dateString.split("/");
			String monthString = dateParts[0];
			String dayString = dateParts[1];
			String yearString = dateParts[2];
			String reformattedDateString = yearString + "-" + monthString + "-" + dayString; 
			Date date = Date.valueOf(reformattedDateString);
			
			return RowFactory.create(symbol, date, openPrice, closePrice);
		});
		
		return rows;
	}
	
	// Read in data sourced from https://finance.yahoo.com/quote
//	public static JavaRDD<Row> readYahooFinanceData(JavaRDD<String> lines) {
//		
//	}
	
	public static Dataset<Row> createDataFrame(JavaRDD<Row> rows) {
		// Create the dataframe schema
		StructField symbol = DataTypes.createStructField("symbol", DataTypes.StringType, false);
		StructField date = DataTypes.createStructField("date", DataTypes.DateType, false);
		StructField openPrice = DataTypes.createStructField("open_price", DataTypes.FloatType, false);
		StructField closePrice = DataTypes.createStructField("close_price", DataTypes.FloatType, false);
		
		StructType schema = DataTypes.createStructType(
				new StructField[] { symbol, date, openPrice, closePrice });
		
		return sparkSession.createDataFrame(rows, schema);
	}
}
