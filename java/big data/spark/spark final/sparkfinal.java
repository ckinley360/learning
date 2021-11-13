package stubs;

import java.io.File;
import java.sql.Date;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class sparkfinal {

	// Create the configuration and context objects
	public static SparkConf conf = new SparkConf();
	public static JavaSparkContext sc = new JavaSparkContext("local", "Spark Final", conf);
	public static SparkSession sparkSession = SparkSession.builder().master("local").appName("Spark Final").config(conf).getOrCreate();
	
	public static void main(String[] args) {
		// Receive the filepath arguments and create File objects with them 
		File alkFile = new File(args[0]);
		File amznFile = new File(args[1]);
		File atviFile = new File(args[2]);
		File coinFile = new File(args[3]);
		File dbxFile = new File(args[4]);
		File docuFile = new File(args[5]);
		File googFile = new File(args[6]);
		File googlFile = new File(args[7]);
		File jnjFile = new File(args[8]);
		File jpmFile = new File(args[9]);
		File msftFile = new File(args[10]);
		File nflxFile = new File(args[11]);
		File nvdaFile = new File(args[12]);
		File psxFile = new File(args[13]);
		File pyplFile = new File(args[14]);
		File qcomFile = new File(args[15]);
		File rdfnFile = new File(args[16]);
		File rostFile = new File(args[17]);
		File sbuxFile = new File(args[18]);
		File sqFile = new File(args[19]);
		
		// Read in the data
		JavaRDD<Row> alkRows = readNasdaqData(sc.textFile(alkFile.getPath()), alkFile.getName().replace(".csv", ""));
		JavaRDD<Row> amznRows = readNasdaqData(sc.textFile(amznFile.getPath()), amznFile.getName().replace(".csv", ""));
		JavaRDD<Row> atviRows = readNasdaqData(sc.textFile(atviFile.getPath()), atviFile.getName().replace(".csv", ""));
		JavaRDD<Row> coinRows = readYahooFinanceData(sc.textFile(coinFile.getPath()), coinFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> dbxRows = readNasdaqData(sc.textFile(dbxFile.getPath()), dbxFile.getName().replace(".csv", ""));
		JavaRDD<Row> docuRows = readYahooFinanceData(sc.textFile(docuFile.getPath()), docuFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> googRows = readNasdaqData(sc.textFile(googFile.getPath()), googFile.getName().replace(".csv", ""));
		JavaRDD<Row> googlRows = readNasdaqData(sc.textFile(googlFile.getPath()), googlFile.getName().replace(".csv", ""));
		JavaRDD<Row> jnjRows = readNasdaqData(sc.textFile(jnjFile.getPath()), jnjFile.getName().replace(".csv", ""));
		JavaRDD<Row> jpmRows = readNasdaqData(sc.textFile(jpmFile.getPath()), jpmFile.getName().replace(".csv", ""));
		JavaRDD<Row> msftRows = readYahooFinanceData(sc.textFile(msftFile.getPath()), msftFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> nflxRows = readYahooFinanceData(sc.textFile(nflxFile.getPath()), nflxFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> nvdaRows = readYahooFinanceData(sc.textFile(nvdaFile.getPath()), nvdaFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> psxRows = readNasdaqData(sc.textFile(psxFile.getPath()), psxFile.getName().replace(".csv", ""));
		JavaRDD<Row> pyplRows = readNasdaqData(sc.textFile(pyplFile.getPath()), pyplFile.getName().replace(".csv", ""));
		JavaRDD<Row> qcomRows = readNasdaqData(sc.textFile(qcomFile.getPath()), qcomFile.getName().replace(".csv", ""));
		JavaRDD<Row> rdfnRows = readYahooFinanceData(sc.textFile(rdfnFile.getPath()), rdfnFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> rostRows = readYahooFinanceData(sc.textFile(rostFile.getPath()), rostFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> sbuxRows = readYahooFinanceData(sc.textFile(sbuxFile.getPath()), sbuxFile.getName().replace(".csv", "").toLowerCase());
		JavaRDD<Row> sqRows = readNasdaqData(sc.textFile(sqFile.getPath()), sqFile.getName().replace(".csv", ""));
		
		// Create a dataframe for each stock dataset
		Dataset<Row> alkDataFrame = createDataFrame(alkRows);
		Dataset<Row> amznDataFrame = createDataFrame(amznRows);
		Dataset<Row> atviDataFrame = createDataFrame(atviRows);
		Dataset<Row> coinDataFrame = createDataFrame(coinRows);
		Dataset<Row> dbxDataFrame = createDataFrame(dbxRows);
		Dataset<Row> docuDataFrame = createDataFrame(docuRows);
		Dataset<Row> googDataFrame = createDataFrame(googRows);
		Dataset<Row> googlDataFrame = createDataFrame(googlRows);
		Dataset<Row> jnjDataFrame = createDataFrame(jnjRows);
		Dataset<Row> jpmDataFrame = createDataFrame(jpmRows);
		Dataset<Row> msftDataFrame = createDataFrame(msftRows);
		Dataset<Row> nflxDataFrame = createDataFrame(nflxRows);
		Dataset<Row> nvdaDataFrame = createDataFrame(nvdaRows);
		Dataset<Row> psxDataFrame = createDataFrame(psxRows);
		Dataset<Row> pyplDataFrame = createDataFrame(pyplRows);
		Dataset<Row> qcomDataFrame = createDataFrame(qcomRows);
		Dataset<Row> rdfnDataFrame = createDataFrame(rdfnRows);
		Dataset<Row> rostDataFrame = createDataFrame(rostRows);
		Dataset<Row> sbuxDataFrame = createDataFrame(sbuxRows);
		Dataset<Row> sqDataFrame = createDataFrame(sqRows);
		
		// Create a temptable for each stock dataframe
		alkDataFrame.createOrReplaceTempView("alk");
		amznDataFrame.createOrReplaceTempView("amzn");
		atviDataFrame.createOrReplaceTempView("atvi");
		coinDataFrame.createOrReplaceTempView("coin");
		dbxDataFrame.createOrReplaceTempView("dbx");
		docuDataFrame.createOrReplaceTempView("docu");
		googDataFrame.createOrReplaceTempView("goog");
		googlDataFrame.createOrReplaceTempView("googl");
		jnjDataFrame.createOrReplaceTempView("jnj");
		jpmDataFrame.createOrReplaceTempView("jpm");
		msftDataFrame.createOrReplaceTempView("msft");
		nflxDataFrame.createOrReplaceTempView("nflx");
		nvdaDataFrame.createOrReplaceTempView("nvda");
		psxDataFrame.createOrReplaceTempView("psx");
		pyplDataFrame.createOrReplaceTempView("pypl");
		qcomDataFrame.createOrReplaceTempView("qcom");
		rdfnDataFrame.createOrReplaceTempView("rdfn");
		rostDataFrame.createOrReplaceTempView("rost");
		sbuxDataFrame.createOrReplaceTempView("sbux");
		sqDataFrame.createOrReplaceTempView("sq");
		
		// Union the datasets
		Dataset<Row> combinedStockData = sparkSession.sql("WITH unionedData AS (\n" + 
				"    SELECT symbol, date, open_price, close_price FROM alk\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM amzn\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM atvi\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM coin\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM dbx\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM docu\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM goog\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM googl\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM jnj\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM jpm\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM msft\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM nflx\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM nvda\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM psx\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM pypl\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM qcom\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM rdfn\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM rost\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM sbux\n" + 
				"    UNION\n" + 
				"    SELECT symbol, date, open_price, close_price FROM sq\n" + 
				")\n" + 
				"SELECT symbol, date, open_price, close_price\n" + 
				"FROM unionedData");
		
		combinedStockData.createOrReplaceTempView("combined_stocks");
		Dataset<Row> runningAverageStockData = sparkSession.sql("SELECT symbol, date, open_price, close_price, AVG(open_price) OVER (PARTITION BY symbol ORDER BY date) AS open_price_running_avg, AVG(close_price) OVER (PARTITION BY symbol ORDER BY date) AS close_price_running_avg FROM combined_stocks ORDER BY symbol, date");
		
		// Output the combined data
		runningAverageStockData.javaRDD().collect().forEach((Row row) -> {
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
			float closePrice = Float.parseFloat(parts[1].replace("$", "")); // Remove the $ from close price
			float openPrice = Float.parseFloat(parts[3].replace("$", "")); // Remove the $ from open price
			
			// Convert the date string to yyyy-mm-dd format so we can create a Date object
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
	
		// Read in data sourced from Read in data sourced from https://finance.yahoo.com/quote
		// Date,Open,High,Low,Close,Adj Close,Volume
		// 2021-04-14,381.000000,429.540009,310.000000,328.279999,328.279999,81065700
		public static JavaRDD<Row> readYahooFinanceData(JavaRDD<String> lines, String symbol) {
			// Delete the header row
			String headerRow = lines.first();
			JavaRDD<String> headerlessLines = lines.filter((String line) -> !line.equals(headerRow));
			
			JavaRDD<Row> rows = headerlessLines.map((String line) -> {
				String[] parts = line.split(",");
				
				String dateString = parts[0];
				float closePrice = Float.parseFloat(parts[4]);
				float openPrice = Float.parseFloat(parts[1]);
				
				// Create a Date object from the dateString 
				Date date = Date.valueOf(dateString);
				
				return RowFactory.create(symbol, date, openPrice, closePrice);
			});
			
			return rows;
		}
	
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
