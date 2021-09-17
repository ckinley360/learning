package stubs;

import java.util.ArrayList;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;
import org.apache.hadoop.conf.Configuration;

import model.FireCall;

public class FirePriorityParseDoFN extends DoFn<FireCall, Pair<Integer, FireCall>> {

	private static final long serialVersionUID = 1226893760849609277L;
	private static ArrayList<Integer> callLevels = new ArrayList<Integer>();

	@Override
	public void process(FireCall call, Emitter<Pair<Integer, FireCall>> emitter) {
		// If the call level of the FireCall is in the user-specified list of call levels, then emit it.
		if (callLevels.contains(call.getPriority())) {
			emitter.emit(new Pair<Integer, FireCall>(call.getPriority(), call));
		}
	}
	
	@Override
	public void configure(Configuration conf) {
		// Since the call_levels parameter is a semicolon-separated list, we must parse it into an ArrayList<Integer> so we can use it as a filter
		String[] callLevelString = conf.get("call_levels").split(";");
		
		for (String callLevel : callLevelString) {
			callLevels.add(Integer.parseInt(callLevel));
		}
	}
}
