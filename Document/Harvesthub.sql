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
WHERE email = 'abhi@gmail.com';
DELETE FROM users
WHERE id = 2;
DELETE FROM crops;
ALTER TABLE harvests AUTO_INCREMENT = 1;
SHOW COLUMNS FROM harvesthub.USERS;
ALTER TABLE harvesthub.USERS
ADD CONSTRAINT uk_users_email UNIQUE (email);
SHOW COLUMNS FROM harvesthub.USERS;
SELECT * FROM harvesthub.USERS;
SELECT * FROM harvesthub.CROPS;
UPDATE harvesthub.USERS
SET id = 2
WHERE id = 3;
DESCRIBE crops;
SELECT id, crop_name, crop_type, season, duration, user_id
FROM harvesthub.CROPS;
ALTER TABLE harvesthub.CROPS AUTO_INCREMENT = 1;