package stubs;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

public class makeFireCostPerDaySecondarySortableDoFN extends DoFn<Pair<String, Double>, Pair<String, Pair<String, Double>>> {

	private static final long serialVersionUID = 5549156473151346347L;

	@Override
	public void process(Pair<String, Double> input, Emitter<Pair<String, Pair<String, Double>>> emitter) {
		emitter.emit(new Pair<String, Pair<String, Double>>("fire", input));
	}
}
