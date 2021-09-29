WITH no_jurisdiction AS (
  SELECT COUNT(*) countnojurisdiction
  FROM policecalls
  WHERE jurisdiction = ''
),

yes_jurisdiction AS (
  SELECT COUNT(*) AS countyesjurisdiction
  FROM policecalls
  WHERE jurisdiction != ''
)

SELECT no_jurisdiction.countnojurisdiction,
       yes_jurisdiction.countyesjurisdiction,
       no_jurisdiction.countnojurisdiction / yes_jurisdiction.countyesjurisdiction AS percentnojurisdiction
FROM no_jurisdiction
JOIN yes_jurisdiction ON 1 = 1;
