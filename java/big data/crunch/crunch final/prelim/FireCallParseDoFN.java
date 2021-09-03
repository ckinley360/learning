package prelim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

import model.FireCall;

public class FireCallParseDoFN extends DoFn<String, FireCall> {

	private static final long serialVersionUID = 1L;
	
	/*
	 * Example data:
	 * "ALARM_LEVEL","CALL_TYPE","JURISDICTION","STATION","RECEIVED_DATE","RECEIVED_TIME","DISPATCH_1ST_TIME","ONSCENE_1ST_TIME","FIRE_CONTROL_TIME","CLOSE_TIME"
	 * "1","UNK/ EMS","RF","UNK","12/31/2011","23:54:40","","","","00:03:54"
	 */

	private static Pattern inputPattern;
	
	private static final Log LOG = LogFactory.getLog(FireCallParseDoFN.class);
	
	private SimpleDateFormat yearFormat;
	private SimpleDateFormat timeFormat;
	
	@Override
	public void initialize() {
		inputPattern = Pattern.compile("^(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*)$");
		
		yearFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		timeFormat = new SimpleDateFormat("HHmmss");
	}
	
	@Override
	public void process(String line, Emitter<FireCall> emitter) {
		Matcher inputMatch = inputPattern.matcher(line);
		
		if (inputMatch.matches()) {
			int priority = inputMatch.group(1).equals("\"\"") ? 1 : Integer.parseInt(inputMatch.group(1).replace("\"", "")); // If this element only contains double quotes, then replace them with a 1.
			String callType = inputMatch.group(2).replace("\"", "");
			String jurisdiction = inputMatch.group(3).replace("\"", "");
			String station = inputMatch.group(4).replace("\"", "");
			Long receiveTime = null;
			Long dispatchTime = null;
			Long arrivalTime = null;
			Long clearTime = null;
			
			// Assign a long to each time field, which is the datetime created by combining the time and callDate
			try {
				String callDateString = inputMatch.group(5).replace("\"", "");
				Date callDate = yearFormat.parse(callDateString);
				receiveTime = inputMatch.group(6).equals("\"\"") ? getFullTime("000000", callDate) : getFullTime(inputMatch.group(6).replace("\"", "").replace(":", ""), callDate);
				dispatchTime = inputMatch.group(7).equals("\"\"") ? getFullTime("000000", callDate) : getFullTime(inputMatch.group(7).replace("\"", "").replace(":", ""), callDate); 
				arrivalTime = inputMatch.group(8).equals("\"\"") ? getFullTime("000000", callDate) : getFullTime(inputMatch.group(8).replace("\"", "").replace(":", ""), callDate); 
				clearTime = inputMatch.group(10).equals("\"\"") ? getFullTime("000000", callDate) : getFullTime(inputMatch.group(10).replace("\"", "").replace(":", ""), callDate);
			} catch (ParseException e) {
				LOG.error("Exception thrown while parsing date. Input was \"" + line + "\".", e);
			}
			
			FireCall fireCall = new FireCall();
			fireCall.setPriority(priority);
			fireCall.setCallType(callType);
			fireCall.setJurisdiction(jurisdiction);
			fireCall.setStation(station);
			fireCall.setReceiveTime(receiveTime);
			fireCall.setDispatchTime(dispatchTime);
			fireCall.setArrivalTime(arrivalTime);
			fireCall.setClearTime(clearTime);
			
			emitter.emit(fireCall);
		} else {
			LOG.warn("Could not parse. Input was \"" + line + "\".");
		}
			
	}
	
	// Produces a long representing the datetime created by combining the time and callDate
	private long getFullTime(String time, Date callDate) throws ParseException {
		Date callTime = timeFormat.parse(time);
		
		long totalTime = callTime.getTime();
		totalTime += callDate.getTime();
		
		return totalTime;
	}
}
