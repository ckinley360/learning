SELECT *
FROM policecalls
LEFT JOIN jurisdictions ON policecalls.jurisdiction = jurisdictions.code
WHERE jurisdictions.code IS NULL
LIMIT 10;
