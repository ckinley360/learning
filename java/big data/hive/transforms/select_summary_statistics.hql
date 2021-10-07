ADD FILE /path/to/calculate_summary_statistics.py;

SELECT
TRANSFORM(priority, calltype, jurisdiction, dispatcharea, receiveddate, receivedtime, dispatchtime, arrivaltime, clearedtime, disposition)
USING 'calculate_summary_statistics.py' AS calltimeaverage, calltimestandarddeviation
FROM policecalls
WHERE receiveddate = ' 01/01/2012';
