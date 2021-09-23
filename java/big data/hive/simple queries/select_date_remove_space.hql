SELECT ReceivedDate,
       LTRIM(ReceivedDate)
FROM PoliceCalls
LIMIT 10;
