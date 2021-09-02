package stubs;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

import model.PoliceCall;

public class PolicePriorityParseDoFN extends DoFn<PoliceCall, Pair<Integer, PoliceCall>> {

	private static final long serialVersionUID = 4437807817795435395L;

	@Override
	public void process(PoliceCall call, Emitter<Pair<Integer, PoliceCall>> emitter) {
		emitter.emit(new Pair<Integer, PoliceCall>(call.getPriority(), call));
	}
}
