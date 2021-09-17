package stubs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;
import org.apache.hadoop.conf.Configuration;

public class SecondarySortRunningAverageDoFN extends DoFn<Pair<String, Iterable<Pair<String, Double>>>, Pair<String, Pair<String, Double>>> {

	private static final long serialVersionUID = -6690441199918684556L;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
	private static LocalDate runningAverageEndDate;
	
	@Override
	public void process(Pair<String, Iterable<Pair<String, Double>>> input, Emitter<Pair<String, Pair<String, Double>>> emitter) {
		String callType = input.first();
		double count = 0.0;
		double sum = 0.0;
		
		for (Pair<String, Double> dateAndCost : input.second()) {
			String date = dateAndCost.first();
			LocalDate convertedDate = LocalDate.parse(date, formatter);
			Double cost = dateAndCost.second();
			count++;
			sum += cost;
			double average = sum / count;
			Pair<String, Double> dateAndCostRunningAverage = new Pair<String, Double>(date, average);
			
			// If the date is on or before the user-specified end date, then emit it.
			if (convertedDate.isBefore(runningAverageEndDate) || convertedDate.isEqual(runningAverageEndDate)) {
				emitter.emit(new Pair<String, Pair<String, Double>>(callType, dateAndCostRunningAverage));
			}
		}
	}
	
	// https://hadoopsters.com/2015/11/11/apache-crunch-tutorial-5-hadoop-configurations/
	// https://hadoopsters.com/2015/11/11/apache-crunch-tutorial-6-configurations-on-a-per-job-basis/
	@Override
	public void configure(Configuration conf) {
		runningAverageEndDate = LocalDate.parse(conf.get("running_average_end_date"), formatter);
	}
}
