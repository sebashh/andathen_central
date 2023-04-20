-- Checking foreign keys
SELECT TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_SCHEMA = 'andathen' AND REFERENCED_TABLE_NAME = 'skill' AND REFERENCED_COLUMN_NAME = 'prerequisite_name';

-- Check all foreign keys
select table_name, constraint_name from information_schema.table_constraints where constraint_type = 'FOREIGN KEY' and constraint_schema = 'andathen';
