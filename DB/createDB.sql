CREATE DATABASE translator CHARACTER SET 'utf8';

USE translator;

CREATE TABLE languages (
                abreviation CHAR(2) NOT NULL,
                name VARCHAR(100) NOT NULL,
                PRIMARY KEY (abreviation)
);


CREATE TABLE subtitles (
                id INT AUTO_INCREMENT NOT NULL,
                title VARCHAR(100) NOT NULL,
                sub LONGBLOB NOT NULL,
                language_abreviation CHAR(2) NOT NULL,
                original_id INT,
                PRIMARY KEY (id)
);

CREATE UNIQUE INDEX subtitles_idx
 ON subtitles
 ( title ASC, language_abreviation ASC );

ALTER TABLE subtitles ADD CONSTRAINT languages_subtitles_fk
FOREIGN KEY (language_abreviation)
REFERENCES languages (abreviation)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE subtitles ADD CONSTRAINT subtitles_subtitles_fk
FOREIGN KEY (original_id)
REFERENCES subtitles (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO languages ( abreviation, name)
VALUES
	('fr', 'Français'),
    ('en', 'English'),
    ('de', 'Deutsch'),
    ('it', 'Italiano'),
    ('pt', 'Português'),
    ('es', 'Español');