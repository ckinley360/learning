package stubs;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

// This class takes the grouped table and emits a pair consisting of the dispatch date and a string containing the priority and total cost
public class PoliceCallBreakdownsDoFN extends DoFn<Pair<String, Iterable<Pair<Integer, Double>>>, Pair<String, String>> {

	private static final long serialVersionUID = -4072888308203440195L;

	@Override
	public void process(Pair<String, Iterable<Pair<Integer, Double>>> dayToPriorityAndCost, Emitter<Pair<String, String>> emitter) {
		// Get the day that we are calculating the costs for
		String day = dayToPriorityAndCost.first();
		
		// Create an array to keep track of the costs for each priority level
		double[] priorityBreakdowns = new double[10];
		
		// Go through all priorities and costs per call
		for (Pair<Integer, Double> priorityAndCost : dayToPriorityAndCost.second()) {
			priorityBreakdowns[priorityAndCost.first()] += priorityAndCost.second();
		}
		
		// Create a string consisting of the breakdown of the cost for each of the priority levels
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < priorityBreakdowns.length; i++) {
			builder.append("Priority level ").append(i).append(" is ").append(priorityBreakdowns[i]).append(".");
		}
		
		emitter.emit(new Pair<String, String>(day, builder.toString()));
	}
}
