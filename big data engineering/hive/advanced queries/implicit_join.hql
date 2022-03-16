SELECT *
FROM policecalls, jurisdictions
WHERE policecalls.jurisdiction = jurisdictions.code
LIMIT 10;
