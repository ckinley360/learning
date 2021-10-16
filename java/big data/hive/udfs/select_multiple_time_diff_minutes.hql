ADD JAR /path/to/jar/callcalculator.jar;
CREATE TEMPORARY FUNCTION multiple_time_diff_minutes AS 'stubs.CallCalculator_MultipleTimeDifferences';

DESCRIBE FUNCTION multiple_time_diff_minutes;
DESCRIBE FUNCTION EXTENDED multiple_time_diff_minutes;

SELECT receiveddate,
       receivedtime,
       dispatchtime,
       arrivaltime,
       clearedtime,
       multiple_time_diff_minutes(receiveddate, receivedtime, dispatchtime, arrivaltime, clearedtime) AS result_map
FROM policecalls
LIMIT 10;
