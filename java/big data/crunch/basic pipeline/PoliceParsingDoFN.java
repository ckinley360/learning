package stubs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.PoliceCall;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

public class PoliceParsingDoFN extends DoFn<String, PoliceCall> {

	private static final long serialVersionUID = -5128505824415122639L;
	/*** 
	 * Example data:
	 * Priority,Call_Type,Jurisdiction,Dispatch_Area,Received_Date,Received_Time,Dispatch_Time,Arrival_Time,Cleared_Time,Disposition
	 * 3,SUSPV,RP,RS, 03/21/2013,173011,182946,182946,183107,OK
	 */
	private static Pattern inputPattern;
	private static final Log LOG = LogFactory.getLog(PoliceParsingDoFN.class);
	
	@Override
	public void initialize() {
		inputPattern = Pattern.compile("([E0-9]+),([A-Z0-9]*),([A-Z]*),([A-Z0-9]*),\\s(\\d{2}\\/\\d{2}\\/\\d{4}),(\\d+),(\\d+),(\\d+),(\\d+),(.*)");
	}
	
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
			
			PoliceCall policeCall = new PoliceCall();
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
		} else {
			LOG.warn("Could not parse. Input was \"" + line + "\".");
		}
	}
}
