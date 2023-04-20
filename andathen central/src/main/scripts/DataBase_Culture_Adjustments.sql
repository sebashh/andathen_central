ALTER TABLE species DROP PRIMARY KEY;
UPDATE species JOIN (select @rank :=0) r SET id=@rank:=@rank+1;
ALTER TABLE species ADD PRIMARY KEY (id);
UPDATE hibernate_sequence SET next_val = 1150;

ALTER TABLE planet_cultures DROP PRIMARY KEY;
ALTER TABLE planet_cultures DROP CONSTRAINT FKaoio658dtl0c55glhkodugiwd;
ALTER TABLE planet_cultures DROP COLUMN cultureNames_KEY;
ALTER TABLE planet_cultures ADD COLUMN culture_id BIGINT;

ALTER TABLE culture DROP PRIMARY KEY;
UPDATE culture JOIN (select @rank :=0) r SET culture_id=@rank:=@rank+1;
ALTER TABLE culture ADD PRIMARY KEY (culture_id);

ALTER TABLE planet_cultures ADD FOREIGN KEY (culture_id) REFERENCES culture(culture_id);
ALTER TABLE planet_cultures ADD CONSTRAINT `FK1dajm23gi421fa24rx8rlfanh` FOREIGN KEY (`planet_designation`) REFERENCES `known_planet` (`designation`);
ALTER TABLE planet_cultures ADD PRIMARY KEY (planet_designation, culture_id);

ALTER TABLE culture DROP CONSTRAINT FKo718un7yfi9p9xcpntu3ae9fr;
ALTER TABLE culture DROP COLUMN species_name;
