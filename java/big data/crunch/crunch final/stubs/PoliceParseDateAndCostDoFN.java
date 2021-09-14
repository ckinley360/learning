package stubs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

import model.PoliceCall;

//This class switches the key & value of the input from <Priority, <Cost, PoliceCall>> to <Dispatch Date, Cost>
public class PoliceParseDateAndCostDoFN extends DoFn<Pair<Integer, Pair<Double, PoliceCall>>, Pair<String, Double>> {

	private static final long serialVersionUID = -5536655071136812808L;
	
	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	@Override
	public void process(Pair<Integer, Pair<Double, PoliceCall>> input, Emitter<Pair<String, Double>> emitter) {
		// Convert the call date to a string
		Date dispatchDate = new Date(input.second().second().getDispatchTime());
		String dispatchDateString = yearFormat.format(dispatchDate);
		
		// Emit <Dispatch Date (in string format), Cost>
		emitter.emit(new Pair<String, Double>(dispatchDateString, input.second().first()));
	}
}
