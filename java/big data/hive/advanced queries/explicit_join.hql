SELECT *
FROM policecalls
JOIN jurisdictions ON policecalls.jurisdiction = jurisdictions.code
LIMIT 10;
