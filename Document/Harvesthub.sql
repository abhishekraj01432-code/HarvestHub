CREATE DATABASE IF NOT EXISTS harvesthub;
USE harvesthub;
SHOW DATABASES;
SELECT * FROM harvests;
SELECT * FROM farm_profiles;
SELECT * FROM fields;
SELECT * FROM crops;
SELECT * FROM activities;
SELECT * FROM crop_plans;
SELECT * FROM maintenance;
SELECT * FROM equipment;
SELECT * FROM expenses;
SELECT * FROM rotation_records;
SHOW TABLES;
SELECT * FROM users
WHERE email = 'abhi@gmail.com';
DELETE FROM users
WHERE id = 2;
DELETE FROM harvests;
ALTER TABLE harvests AUTO_INCREMENT = 1;