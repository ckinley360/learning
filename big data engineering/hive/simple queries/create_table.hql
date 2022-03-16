CREATE TABLE PoliceCalls (
  Priority STRING,
  CallType STRING,
  Jurisdiction STRING,
  DispatchArea STRING,
  ReceivedDate STRING,
  ReceivedTime INT,
  DispatchTime INT,
  ArrivalTime INT,
  ClearedTime INT,
  Disposition STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ',';
