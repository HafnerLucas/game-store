
CREATE EXTENSION unaccent;

CREATE TABLE Platform (
	id                    SMALLSERIAL PRIMARY KEY,
	type                  text NOT NULL
);

INSERT INTO Platform (type) values
('Nintendo'),
('Playstation'),
('Xbox');