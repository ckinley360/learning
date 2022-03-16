package stubs;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Description(name = "time_diff_minutes",
             value = "_FUNC_(date, startTime, endTime) - Calculates the difference in minutes.",
             extended = "Example:\n" + " > SELECT _FUNC_(' 10/7/2021', 102245, 133611) FROM src LIMIT 1;\n")

public class CallCalculator_DynamicDateFormat extends UDF {

	public LongWritable evaluate(Text dateFormat, Text date, IntWritable startTime, IntWritable endTime) {
		// Attempt to use the parameterized date format. If there is an error, then use the default format.
		DateTimeFormatter formatter;
		
		try {
			formatter = DateTimeFormatter.ofPattern(dateFormat.toString());
		} catch (Exception e){
			formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		}
		
		// If the date is bad data, then return 0. Otherwise, parse it.
		String stringDate = date.toString().trim();
		LocalDate parsedDate;
		
		try {
			parsedDate = LocalDate.parse(stringDate, formatter);
		} catch (Exception e) {
			//System.out.println("Exception thrown while parsing date. Input was \"" + stringDate + "\"." + "\n" + e);
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
		LocalDateTime startDateTime = LocalDateTime.of(parsedDate, LocalTime.of(startTimeHour, startTimeMinute, startTimeSecond));
		LocalDateTime endDateTime = LocalDateTime.of(parsedDate, LocalTime.of(endTimeHour, endTimeMinute, endTimeSecond));
		
		// Calculate the time difference between the start datetime and end datetime.
		Duration duration = Duration.between(startDateTime, endDateTime);
		
		// Return the time difference in minutes.
		return new LongWritable(duration.toMinutes());
	}
}
