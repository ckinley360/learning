package stubs;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Description(name = "time_diff_seconds",
             value = "_FUNC_(date, startTime, endTime) - Calculates the difference in seconds.",
             extended = "Example:\n" + " > SELECT _FUNC_('2021-10-07', 082245, 103611) FROM src LIMIT 1;\n")

public class CallCalculator extends UDF {

	public LongWritable evaluate(Text date, IntWritable startTime, IntWritable endTime) {
		// If the date is bad data, then return 0. Otherwise, parse it.
		String stringDate = date.toString().trim();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDateTime parsedDate;
		
		try {
			parsedDate = LocalDateTime.parse(stringDate, formatter);
		} catch (Exception e) {
			System.out.println("Exception thrown while parsing date. Input was \"" + stringDate + "\".");
			return new LongWritable(0);
		}
		
		// If the start and end times are bad data, then use 12:00:00am for them. Otherwise, parse them.
		Pattern timePattern = Pattern.compile("[0-9]{6}");
		String startTimeString = startTime.toString();
		String endTimeString = endTime.toString();
		Matcher startTimeMatch = timePattern.matcher(startTimeString);
		Matcher endTimeMatch = timePattern.matcher(endTimeString);
		int startTimeHour = 0;
		int startTimeMinute = 0;
		int startTimeSecond = 0;
		int endTimeHour = 0;
		int endTimeMinute = 0;
		int endTimeSecond = 0;
		
		if (startTimeMatch.matches()) {
			startTimeHour = Integer.parseInt(startTimeString.substring(0, 2));
			startTimeMinute = Integer.parseInt(startTimeString.substring(2, 4));
			startTimeSecond = Integer.parseInt(startTimeString.substring(4, 6));
		}
		
		if (endTimeMatch.matches()) {
			endTimeHour = Integer.parseInt(endTimeString.substring(0, 2));
			endTimeMinute = Integer.parseInt(endTimeString.substring(2, 4));
			endTimeSecond = Integer.parseInt(endTimeString.substring(4, 6));
		}
		
		// Create two datetime objects - date & start time, date & end time.
		LocalDateTime startDateTime = parsedDate.plusHours(startTimeHour).plusMinutes(startTimeMinute).plusSeconds(startTimeSecond);
		LocalDateTime endDateTime = parsedDate.plusHours(endTimeHour).plusMinutes(endTimeMinute).plusSeconds(endTimeSecond);
		
		// Calculate the time difference between the start datetime and end datetime.
		Duration duration = Duration.between(startDateTime, endDateTime);
		
		// Return the time difference in minutes.
		return new LongWritable(duration.toMinutes());
	}
}
