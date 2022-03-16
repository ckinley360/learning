package stubs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.Pair;

public class CallCostParseDoFN extends DoFn<String, Pair<Integer, Double>> {

	private static final long serialVersionUID = 2136113286681640460L;
	/***
	 * Example data:
	 * Priority,Cost
	 * 0,1003.91
	 */
	private static Pattern inputPattern = Pattern.compile("(\\d),(\\d*\\.\\d*)");
	private static final Log LOG = LogFactory.getLog(CallCostParseDoFN.class);
	
	@Override
	public void process(String line, Emitter<Pair<Integer, Double>> emitter) {
		Matcher inputMatch = inputPattern.matcher(line);
		
		if (inputMatch.matches()) {
			Integer priority = Integer.parseInt(inputMatch.group(1));
			Double cost = Double.parseDouble(inputMatch.group(2));
			
			emitter.emit(new Pair<Integer, Double>(priority, cost));
		} else {
			LOG.warn("Could not parse. Input was \"" + line + "\".");
		}
	}
}
