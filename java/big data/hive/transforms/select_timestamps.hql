ADD FILE /path/to/timestampify.py;

SELECT
TRANSFORM(priority, calltype, jurisdiction, dispatcharea, receiveddate, receivedtime, dispatchtime, arrivaltime, clearedtime, disposition)
USING 'timestampify.py' AS priority, calltype, jurisdiction, dispatcharea, receiveddate, receivedtime, dispatchtime, arrivaltime, clearedtime, disposition
FROM policecalls
LIMIT 10;
