package stubs;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

import model.PoliceCall;

public class PoliceJurisdictionAndDispatchAreaParseDoFN extends DoFn<PoliceCall, Pair<String, String>> {

	private static final long serialVersionUID = 9166752919165092452L;

	@Override
	public void process(PoliceCall call, Emitter<Pair<String, String>> emitter) {
		emitter.emit(new Pair<String, String>(call.getJurisdiction().toString(), call.getDispatchArea().toString()));
	}
}
