ADD FILE /path/to/calculate_call_time.py;

SELECT
TRANSFORM(priority, calltype, jurisdiction, dispatcharea, receiveddate, receivedtime, dispatchtime, arrivaltime, clearedtime, disposition)
USING 'calculate_call_time.py' AS calltime
FROM policecalls
WHERE receiveddate = ' 01/01/2012'
ORDER BY calltime DESC
LIMIT 10;
