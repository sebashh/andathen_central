alter table planet add CONSTRAINT FK_star_designation FOREIGN KEY (star) REFERENCES star (designation);