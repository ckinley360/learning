SELECT ReceivedDate,
       TO_DATE(FROM_UNIXTIME(UNIX_TIMESTAMP(ReceivedDate, 'MM/dd/yyyy')))
FROM PoliceCalls
LIMIT 10;
