-- for peak hours
INSERT INTO HOURS_FOR_FARE_TYPE (days, starttime, endtime, fromzone, tozone, peakhourtype) VALUES
  ('MONDAY', 7.0, 10.30,0,0,1),
  ('TUESDAY', 7.0, 10.30,0,0,1),
  ('WEDNESDAY', 7.0, 10.30,0,0,1),
  ('THRUSDAY', 7.0, 10.30,0,0,1),
  ('FRIDAY', 7.0, 10.30,0,0,1),
  ('SATURDAY', 9.0, 11.0,0,0,1),
  ('SUNDAY', 9.0, 11.0,0,0,1),
  ('MONDAY', 17.0, 20.0,0,0,1),
  ('TUESDAY', 17.0, 20.0,0,0,1),
  ('WEDNESDAY', 17.0, 20.0,0,0,1),
  ('THRUSDAY', 17.0, 20.0,0,0,1),
  ('FRIDAY', 17.0, 20.0,0,0,1),
  ('SATURDAY', 18.0, 22.0,0,0,1),
  ('SUNDAY', 18.0, 22.0,0,0,1);
  
  -- for non peak hours
 INSERT INTO HOURS_FOR_FARE_TYPE (days, starttime, endtime, fromzone, tozone, peakhourtype) VALUES
  ('MONDAY', 17.0, 20.0,2,1,2),
  ('TUESDAY', 17.0, 20.0,2,1,2),
  ('WEDNESDAY', 17.0, 20.0,2,1,2),
  ('THRUSDAY', 17.0, 20.0,2,1,2),
  ('FRIDAY', 17.0, 20.0,2,1,2),
  ('SATURDAY', 18.0, 22.0,2,1,2),
  ('SUNDAY', 18.0, 22.0,2,1,2);
  