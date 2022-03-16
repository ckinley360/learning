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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Description(name = "multiple_time_diff_minutes",
             value = "_FUNC_(date, timeOne, timeTwo, timeThree, timeFour) - Calculates the difference in minutes between timeOne and each of the other three times.",
             extended = "Example:\n" + " > SELECT _FUNC_(' 10/7/2021', 102245, 103303, 110321, 133611) FROM src LIMIT 1;\n")

public class CallCalculator_MultipleTimeDifferences extends UDF {

	public Map<IntWritable, LongWritable> evaluate(Text date, IntWritable timeOne, IntWritable timeTwo, IntWritable timeThree, IntWritable timeFour) {
		// If the date is bad data, then return {0:0}. Otherwise, parse it.
		String stringDate = date.toString().trim();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate parsedDate;
		
		try {
			parsedDate = LocalDate.parse(stringDate, formatter);
		} catch (Exception e) {
			//System.out.println("Exception thrown while parsing date. Input was \"" + stringDate + "\"." + "\n" + e);
			Map<IntWritable, LongWritable> badDataReturnValue = new HashMap<IntWritable, LongWritable>();
			badDataReturnValue.put(new IntWritable(0), new LongWritable(0));
			return badDataReturnValue;
		}
		
		// If any of the times are bad data, then use 12:00:00am for them. Otherwise, parse them.
		Pattern timePattern = Pattern.compile("[0-9]{6}");
		
		String timeOneString = timeOne.toString();
		String timeTwoString = timeTwo.toString();
		String timeThreeString = timeThree.toString();
		String timeFourString = timeFour.toString();
		
		Matcher timeOneMatch = timePattern.matcher(timeOneString);
		Matcher timeTwoMatch = timePattern.matcher(timeTwoString);
		Matcher timeThreeMatch = timePattern.matcher(timeThreeString);
		Matcher timeFourMatch = timePattern.matcher(timeFourString);
		
		int timeOneHour = 0;
		int timeTwoHour = 0;
		int timeThreeHour = 0;
		int timeFourHour = 0;
		
		int timeOneMinute = 0;
		int timeTwoMinute = 0;
		int timeThreeMinute = 0;
		int timeFourMinute = 0;
		
		int timeOneSecond = 0;
		int timeTwoSecond = 0;
		int timeThreeSecond = 0;
		int timeFourSecond = 0;
		
		if (timeOneMatch.matches()) {
			timeOneHour = Integer.parseInt(timeOneString.substring(0, 2));
			timeOneMinute = Integer.parseInt(timeOneString.substring(2, 4));
			timeOneSecond = Integer.parseInt(timeOneString.substring(4, 6));
		}
		
		if (timeTwoMatch.matches()) {
			timeTwoHour = Integer.parseInt(timeTwoString.substring(0, 2));
			timeTwoMinute = Integer.parseInt(timeTwoString.substring(2, 4));
			timeTwoSecond = Integer.parseInt(timeTwoString.substring(4, 6));
		}
		
		if (timeThreeMatch.matches()) {
			timeThreeHour = Integer.parseInt(timeThreeString.substring(0, 2));
			timeThreeMinute = Integer.parseInt(timeThreeString.substring(2, 4));
			timeThreeSecond = Integer.parseInt(timeThreeString.substring(4, 6));
		}
		
		if (timeFourMatch.matches()) {
			timeFourHour = Integer.parseInt(timeFourString.substring(0, 2));
			timeFourMinute = Integer.parseInt(timeFourString.substring(2, 4));
			timeFourSecond = Integer.parseInt(timeFourString.substring(4, 6));
		}
		
		// Create four datetime objects - date & time one, date & time two, date & time three, date & time four.
		LocalDateTime dateTimeOne = LocalDateTime.of(parsedDate, LocalTime.of(timeOneHour, timeOneMinute, timeOneSecond));
		LocalDateTime dateTimeTwo = LocalDateTime.of(parsedDate, LocalTime.of(timeTwoHour, timeTwoMinute, timeTwoSecond));
		LocalDateTime dateTimeThree = LocalDateTime.of(parsedDate, LocalTime.of(timeThreeHour, timeThreeMinute, timeThreeSecond));
		LocalDateTime dateTimeFour = LocalDateTime.of(parsedDate, LocalTime.of(timeFourHour, timeFourMinute, timeFourSecond));
		
		// Calculate the time difference between datetime one and each of the other three datetimes.
		Duration durationOne = Duration.between(dateTimeOne, dateTimeTwo);
		Duration durationTwo = Duration.between(dateTimeOne, dateTimeThree);
		Duration durationThree = Duration.between(dateTimeOne, dateTimeFour);
		
		// Create a HashMap for the return object.
		Map<IntWritable, LongWritable> values = new HashMap<IntWritable, LongWritable>();
		values.put(timeTwo, new LongWritable(durationOne.toMinutes()));
		values.put(timeThree, new LongWritable(durationTwo.toMinutes()));
		values.put(timeFour, new LongWritable(durationThree.toMinutes()));
		
		// Return the time differences in minutes.
		return values;
	}
}
