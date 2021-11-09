package stubs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class sparkfinal {

	public static void main(String[] args) {
		// Receive the arguments
		String alkInput = args[0];
		String amznInput = args[1];
		String atviInput = args[2];
		String coinInput = args[3];
		String dbxInput = args[4];
		String docuInput = args[5];
		String googInput = args[6];
		String googlInput = args[7];
		String jnjInput = args[8];
		String jpmInput = args[9];
		String msftInput = args[10];
		String nflxInput = args[11];
		String nvdaInput = args[12];
		String psxInput = args[13];
		String pyplInput = args[14];
		String qcomInput = args[15];
		String rdfnInput = args[16];
		String rostInput = args[17];
		String sbuxInput = args[18];
		String sqInput = args[19];
		
		// Create the configuration and context objects
		SparkConf conf = new SparkConf();
		JavaSparkContext sc = new JavaSparkContext("local", "Spark Final", conf);
		SQLContext sqlContext = new SQLContext(sc);
		
		// Read in the data
		Dataset<Row> alkDataFrame = sqlContext.read().csv(alkInput);
		Dataset<Row> amznDataFrame = sqlContext.read().csv(amznInput);
		Dataset<Row> atviDataFrame = sqlContext.read().csv(atviInput);
		Dataset<Row> coinDataFrame = sqlContext.read().csv(coinInput);
		Dataset<Row> dbxDataFrame = sqlContext.read().csv(dbxInput);
		Dataset<Row> docuDataFrame = sqlContext.read().csv(docuInput);
		Dataset<Row> googDataFrame = sqlContext.read().csv(googInput);
		Dataset<Row> googlDataFrame = sqlContext.read().csv(googlInput);
		Dataset<Row> jnjDataFrame = sqlContext.read().csv(jnjInput);
		Dataset<Row> jpmDataFrame = sqlContext.read().csv(jpmInput);
		Dataset<Row> msftDataFrame = sqlContext.read().csv(msftInput);
		Dataset<Row> nflxDataFrame = sqlContext.read().csv(nflxInput);
		Dataset<Row> nvdaDataFrame = sqlContext.read().csv(nvdaInput);
		Dataset<Row> psxDataFrame = sqlContext.read().csv(psxInput);
		Dataset<Row> pyplDataFrame = sqlContext.read().csv(pyplInput);
		Dataset<Row> qcomDataFrame = sqlContext.read().csv(qcomInput);
		Dataset<Row> rdfnDataFrame = sqlContext.read().csv(rdfnInput);
		Dataset<Row> rostDataFrame = sqlContext.read().csv(rostInput);
		Dataset<Row> sbuxDataFrame = sqlContext.read().csv(sbuxInput);
		Dataset<Row> sqDataFrame = sqlContext.read().csv(sqInput);
	}
	
	public static JavaRDD
}
