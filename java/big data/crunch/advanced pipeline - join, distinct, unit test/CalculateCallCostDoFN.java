package stubs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

import model.PoliceCall;

// This class switches the key & value of the input from <Priority, <Cost, PoliceCall>> to <Dispatch Date<Priority, Cost>>
public class CalculateCallCostDoFN extends DoFn<Pair<Integer, Pair<Double, PoliceCall>>, Pair<String, Pair<Integer, Double>>> { 

	private static final long serialVersionUID = -6912647033551079222L;
	
	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	@Override
	public void process(Pair<Integer, Pair<Double, PoliceCall>> costAndCall, Emitter<Pair<String, Pair<Integer, Double>>> emitter) {
		// Convert the call date to a string
		Date dispatchDate = new Date(costAndCall.second().second().getDispatchTime());
		String dispatchDateString = yearFormat.format(dispatchDate);
		
		// Create a pair where the first element is the call priority and second element is the total cost of the call
		Pair<Integer, Double> priorityAndCallCost = new Pair<Integer, Double>(costAndCall.first(), costAndCall.second().first());
		
		// <Dispatch date (in string format), <Priority, Cost>>
		emitter.emit(new Pair<String, Pair<Integer, Double>>(dispatchDateString, priorityAndCallCost));
	}
}
