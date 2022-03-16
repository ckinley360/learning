SELECT YEAR(TO_DATE(FROM_UNIXTIME(UNIX_TIMESTAMP(ReceivedDate, 'MM/dd/yyyy')))) AS year,
       COUNT(*) AS countofcalls
FROM policecalls
GROUP BY YEAR(TO_DATE(FROM_UNIXTIME(UNIX_TIMESTAMP(ReceivedDate, 'MM/dd/yyyy'))));
