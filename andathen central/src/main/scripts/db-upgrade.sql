-- Upgrade the resource table
ALTER TABLE resource ADD COLUMN id BIGINT;
ALTER TABLE resource ADD COLUMN image MEDIUMBLOB;
UPDATE resource JOIN (SELECT @rank := 0) r SET id=@rank:=@rank+1;
ALTER TABLE resource DROP PRIMARY KEY, ADD PRIMARY KEY(id);
ALTER TABLE resource MODIFY COLUMN description text;

-- Upgrade the language table
ALTER TABLE language ADD COLUMN id BIGINT;
ALTER TABLE language ADD COLUMN image MEDIUMBLOB;
UPDATE language JOIN (SELECT @rank := 0) r SET id=@rank:=@rank+1;
ALTER TABLE language DROP PRIMARY KEY, ADD PRIMARY KEY(id);
ALTER TABLE language MODIFY COLUMN description text;

-- Upgrade the species table
ALTER TABLE species ADD COLUMN id BIGINT;
ALTER TABLE species ADD COLUMN image MEDIUMBLOB;
UPDATE species JOIN (SELECT @rank := 0) r SET id=@rank:=@rank+1;
ALTER TABLE species DROP PRIMARY KEY, ADD PRIMARY KEY(id);
ALTER TABLE species MODIFY COLUMN description text;

-- Upgrade the skill table
ALTER TABLE skill ADD COLUMN id BIGINT;
UPDATE skill JOIN (SELECT @rank := 0) r SET id=@rank:=@rank+1;
ALTER TABLE skill DROP PRIMARY KEY, ADD PRIMARY KEY(id);
ALTER TABLE skill MODIFY COLUMN description text;
ALTER TABLE skill DROP COLUMN prerequisite_name;
ALTER TABLE skill ADD COLUMN prerequisite BIGINT;
