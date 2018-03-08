CREATE DATABASE translator2 CHARACTER SET 'utf8';

USE translator2;

CREATE TABLE language (
                abreviation CHAR(2) NOT NULL,
                name VARCHAR(100) NOT NULL,
                PRIMARY KEY (abreviation)
);


CREATE TABLE subtitle (
                id INT AUTO_INCREMENT NOT NULL,
                title VARCHAR(100) NOT NULL,
                language_abreviation CHAR(2) NOT NULL,
                original_id INT,
                PRIMARY KEY (id)
);


CREATE UNIQUE INDEX subtitle_idx
 ON subtitle
 ( title ASC, language_abreviation ASC );

CREATE TABLE subtitle_line (
                subtitle_id INT NOT NULL,
                position INT NOT NULL,
                start TIME(3) NOT NULL,
                end TIME(3) NOT NULL,
                line1 VARCHAR(250),
                line2 VARCHAR(250),
                PRIMARY KEY (subtitle_id, position)
);


ALTER TABLE subtitle ADD CONSTRAINT language_subtitle_fk
FOREIGN KEY (language_abreviation)
REFERENCES language (abreviation)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE subtitle ADD CONSTRAINT subtitle_subtitle_fk
FOREIGN KEY (original_id)
REFERENCES subtitle (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE subtitle_line ADD CONSTRAINT subtitle_subtitle_line_fk
FOREIGN KEY (subtitle_id)
REFERENCES subtitle (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO language ( abreviation, name)
VALUES
	('fr', 'Français'),
    ('en', 'English'),
    ('de', 'Deutsch'),
    ('it', 'Italiano'),
    ('pt', 'Português'),
    ('es', 'Español');