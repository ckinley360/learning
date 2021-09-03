package stubs;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

import model.FireCall;

public class FirePriorityParseDoFN extends DoFn<FireCall, Pair<Integer, FireCall>> {

	private static final long serialVersionUID = 1226893760849609277L;

	@Override
	public void process(FireCall call, Emitter<Pair<Integer, FireCall>> emitter) {
		emitter.emit(new Pair<Integer, FireCall>(call.getPriority(), call));
	}
}
