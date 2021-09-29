SELECT priority,
       COUNT(*) AS callcount
FROM policecalls
GROUP BY priority
ORDER BY callcount DESC;
