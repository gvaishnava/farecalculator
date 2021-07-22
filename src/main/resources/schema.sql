-- Master table to keep record of type of fare PEEK HOURS or OFF-PEAK HOURS based on date and zones info
DROP TABLE IF EXISTS HOURS_FOR_FARE_TYPE;
  
CREATE TABLE HOURS_FOR_FARE_TYPE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  days VARCHAR(250) NOT NULL,
  starttime DOUBLE DEFAULT 0,
  endtime DOUBLE DEFAULT 0,
  fromzone INT DEFAULT 0,
  tozone INT DEFAULT 0,
  peakhourtype INT DEFAULT 0
);