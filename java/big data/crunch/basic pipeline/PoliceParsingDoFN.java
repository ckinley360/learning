package stubs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

import model.PoliceCall;

public class PoliceParsingDoFN extends DoFn<String, PoliceCall> {

	private static final long serialVersionUID = -5128505824415122639L;
	private static Pattern inputPattern = Pattern.compile("([E0-9]+),([A-Z0-9]*),([A-Z]*),([A-Z0-9]*),\\s(\\d{2}\\/\\d{2}\\/\\d{4}),(\\d+),(\\d+),(\\d+),(\\d+),(.*)");
	private static final PoliceCall.Builder policeCallBuilder = PoliceCall.newBuilder();
	
	@Override
	public void process(String line, Emitter<PoliceCall> emitter) {
		Matcher inputMatch = inputPattern.matcher(line);
		
		if (inputMatch.matches()) {
			String priority = inputMatch.group(1);
			int convertedPriority = priority.equals("E") ? 0 : Integer.parseInt(priority); // If the priority is "E" (emergency), then convert it to 0 to keep the value numeric.
			String callType = inputMatch.group(2);
			String jurisdiction = inputMatch.group(3);
			String dispatchArea = inputMatch.group(4);
			Long receiveTime = Long.parseLong(inputMatch.group(6)); // We are skipping the 5th group (Receive Date).
			Long dispatchTime = Long.parseLong(inputMatch.group(7));
			Long arrivalTime = Long.parseLong(inputMatch.group(8));
			Long clearTime = Long.parseLong(inputMatch.group(9));
			String disposition = inputMatch.group(10);
			
			PoliceCall policeCall = policeCallBuilder.build();
			policeCall.setPriority(convertedPriority);
			policeCall.setCallType(callType);
			policeCall.setJurisdiction(jurisdiction);
			policeCall.setDispatchArea(dispatchArea);
			policeCall.setReceiveTime(receiveTime);
			policeCall.setDispatchTime(dispatchTime);
			policeCall.setArrivalTime(arrivalTime);
			policeCall.setClearTime(clearTime);
			policeCall.setDisposition(disposition);
			
			emitter.emit(policeCall);
		}
	}
}
