drop table if exists hill;

CREATE TABLE hill(
  id BIGINT,
  name VARCHAR(80),
  height FLOAT,
  category VARCHAR(3)
) AS SELECT id, name, height, category FROM CSVREAD('classpath:munro-data.csv');