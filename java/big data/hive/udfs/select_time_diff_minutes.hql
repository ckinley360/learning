ADD JAR /path/to/jar/callcalculator.jar;
CREATE TEMPORARY FUNCTION time_diff_minutes AS 'stubs.CallCalculator';

DESCRIBE FUNCTION time_diff_minutes;
DESCRIBE FUNCTION EXTENDED time_diff_minutes;

SELECT receiveddate, receivedtime, clearedtime, time_diff_minutes(receiveddate, receivedtime, clearedtime) AS minute_difference
FROM policecalls
LIMIT 10;
