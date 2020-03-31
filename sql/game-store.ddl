CREATE EXTENSION unaccent;

CREATE TABLE platform (
	id                   		 SMALLSERIAL PRIMARY KEY,
	description                  text NOT NULL
);

INSERT INTO platform (description) VALUES
('Nintendo'),
('Playstation'),
('Xbox');

CREATE TABLE category(
	id                    		 SMALLSERIAL PRIMARY KEY,
	description                  text NOT NULL
);

INSERT INTO category(description) VALUES
('Consola'),
('Accesorios'),
('Juegos');

CREATE TABLE product(
	id					 	SERIAL primary key,
	description          	text not null,
	price				 	float not null,	
	quantity 			 	integer not null,
	category_id				integer references Category(id),
	platform_id	            integer references Platform(id),
	constraint no_negative check (price >= 0 and quantity >=0)	
);

INSERT INTO product(description, price, quantity,category_id,platform_id) VALUES
('Playstation 4',35499.99,4,1,2),
('Nintendo Switch',45900,1,1,1),
('Nintendo 3DS XL',19000,5,1,1);