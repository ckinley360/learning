package stubs;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

public class SecondarySortRunningAverageDoFN extends DoFn<Pair<String, Iterable<Pair<String, Double>>>, Pair<String, Pair<String, Double>>> {

	private static final long serialVersionUID = -6690441199918684556L;

	@Override
	public void process(Pair<String, Iterable<Pair<String, Double>>> input, Emitter<Pair<String, Pair<String, Double>>> emitter) {
		String callType = input.first();
		double count = 0.0;
		double sum = 0.0;
		
		for (Pair<String, Double> dateAndCost : input.second()) {
			String date = dateAndCost.first();
			Double cost = dateAndCost.second();
			count++;
			sum += cost;
			double average = sum / count;
			Pair<String, Double> dateAndCostRunningAverage = new Pair<String, Double>(date, average);
			
			emitter.emit(new Pair<String, Pair<String, Double>>(callType, dateAndCostRunningAverage));
		}
	}
}
