package stubs;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.time.LocalDateTime;

@Description(name = "time_diff_seconds",
             value = "_FUNC_(date, startTime, endTime) - Calculates the difference in seconds.",
             extended = "Example:\n" + " > SELECT _FUNC_('2021-10-07', 082245, 103611) FROM src LIMIT 1;\n")

public class CallCalculator extends UDF {

	public LongWritable evaluate(Text date, IntWritable startTime, IntWritable endTime) {
		// Check that the input data is of good quality. If not, ignore it and return 0.
		
	}
}
