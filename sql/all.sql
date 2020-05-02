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


--product

-- CONSTRUCTORES y DESTRUCTORES
CREATE OR REPLACE FUNCTION product (
	IN p_description                 text,
	IN p_price                       float,
	IN p_quantity                    integer,
	IN p_category                    integer,
    IN p_platform                    integer
) RETURNS product AS
$$
	INSERT INTO product(description, price, quantity,category_id,platform_id)
		VALUES (p_description, p_price, p_quantity, p_category, p_platform)
	RETURNING *;
$$ LANGUAGE sql VOLATILE;


CREATE OR REPLACE FUNCTION product_destroy (
	IN p_id                     integer
) RETURNS void AS 
$$
	DELETE FROM Product WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;

-- GETTERS Y SETTERS
-- description
CREATE OR REPLACE FUNCTION product_get_description (
	IN p_id                      integer
) RETURNS text AS
$$ 
	SELECT description FROM product WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_description (
	IN p_id                    			 integer,
	IN p_new_description                 text
) RETURNS void AS 
$$
	UPDATE product SET description = p_new_description WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;


-- price
CREATE OR REPLACE FUNCTION product_get_price (
	IN p_id                      integer
) RETURNS double precision AS
$$ 
	SELECT price FROM product WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_price (
	IN p_id                      	integer,
	IN p_new_price                  float
) RETURNS void AS 
$$
	UPDATE product SET price = p_new_price WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;


-- quantity
CREATE OR REPLACE FUNCTION product_get_quantity (
	IN p_id                      		integer
) RETURNS text AS
$$ 
	SELECT quantity FROM product WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_quantity (
	IN p_id			                  integer,
	IN p_new_quantity                 integer
) RETURNS void AS 
$$
	UPDATE product SET quantity = p_new_quantity WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;


--category
CREATE OR REPLACE FUNCTION product_get_category (
	IN p_id                      		integer
) RETURNS text AS
$$ 
	SELECT c.description 
	FROM product p 
	inner join category c 
		on c.id=p.category_id 
	WHERE p.id = p_id;
$$ LANGUAGE sql STABLE STRICT;



CREATE OR REPLACE FUNCTION product_set_category (
	IN p_id                      		integer,
	IN p_new_category                  	integer
) RETURNS void AS 
$$
	UPDATE product SET category_id = p_new_category WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;


--platform
CREATE OR REPLACE FUNCTION product_get_platform (
	IN p_id                      		integer
) RETURNS text AS
$$ 
	SELECT pl.description 
	FROM product pr 
	inner join platform pl
		on pr.platform_id = pl.id 
	WHERE pr.id = p_id;
$$ LANGUAGE sql STABLE STRICT;

CREATE OR REPLACE FUNCTION product_set_platform (
	IN p_id                      		integer,
	IN p_new_platform                  	integer
) RETURNS void AS 
$$
	UPDATE product SET platform_id = p_new_platform WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;



-- IDENTIFICACION Y BUSQUEDA
CREATE OR REPLACE FUNCTION product_search (
	IN p_description                  text DEFAULT '%'
) RETURNS SETOF product AS 
$$
	SELECT * FROM product 
		WHERE unaccent(description) ilike unaccent('%' || p_description || '%');
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_identify (
	IN p_id                     integer
) RETURNS product AS 
$$
	SELECT * FROM product  WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;

-- LISTADO
CREATE OR REPLACE FUNCTION product_list (
) RETURNS SETOF product_view AS 
$$
SELECT * from product_view
$$ LANGUAGE sql STABLE STRICT;



--Vistas
create view list_products_from_delete as
	select id,description from product


create view product_view as 
	SELECT 
		pr.id, pr.description, pr.price, pr.quantity, ca.description category,pl.description platform 
	FROM product pr 
	inner join category ca
		on ca.id=pr.category_id 
	inner join platform pl
		on pl.id=pr.platform_id

-- category

-- CONSTRUCTORES y DESTRUCTORES
CREATE OR REPLACE FUNCTION category (
	IN p_description                 text
) RETURNS category AS
$$
	INSERT INTO category(description)
		VALUES (p_description)
	RETURNING *;
$$ LANGUAGE sql VOLATILE;

CREATE OR REPLACE FUNCTION category_destroy (
	IN p_id                     integer
) RETURNS void AS 
$$
	DELETE FROM category WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;

-- GETTERS Y SETTERS
-- description
CREATE OR REPLACE FUNCTION category_get_description (
	IN p_id                     integer
) RETURNS text AS
$$ 
	SELECT description FROM category WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION category_set_description (
	IN p_id                               integer,
	IN p_new_description                  text
) RETURNS void AS 
$$
	UPDATE category SET description = p_new_description WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;


-- IDENTIFICACION Y BUSQUEDA
CREATE OR REPLACE FUNCTION search_category_if_contains (
	IN p_description                 text DEFAULT '%'
) RETURNS SETOF category AS 
$$
	SELECT * FROM category 
		WHERE description ilike ('%' || p_description || '%') order by id asc;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION get_category_by_id (
	IN p_id                     integer
) RETURNS category AS 
$$
	SELECT * FROM category  WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;


-- platform

-- CONSTRUCTORES y DESTRUCTORES
CREATE OR REPLACE FUNCTION platform (
	IN p_description                 text
) RETURNS platform AS
$$
	INSERT INTO platform(description)
		VALUES (p_description)
	RETURNING *;
$$ LANGUAGE sql VOLATILE;

CREATE OR REPLACE FUNCTION platform_destroy (
	IN p_id                     integer
) RETURNS void AS 
$$
	DELETE FROM platform WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;

-- GETTERS Y SETTERS
-- description
CREATE OR REPLACE FUNCTION platform_get_description (
	IN p_id                     integer
) RETURNS text AS
$$ 
	SELECT description FROM platform WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION platform_set_description (
	IN p_id                               integer,
	IN p_new_description                  text
) RETURNS void AS 
$$
	UPDATE platform SET description = p_new_description WHERE id = p_id;
$$ LANGUAGE sql VOLATILE STRICT;


-- IDENTIFICACION Y BUSQUEDA
CREATE OR REPLACE FUNCTION search_platform_if_contains (
	IN p_description                 text DEFAULT '%'
) RETURNS SETOF platform AS 
$$
	SELECT * FROM platform 
		WHERE description ilike ('%' || p_description || '%') order by id asc;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION get_platform_by_id (
	IN p_id                     integer
) RETURNS platform AS 
$$
	SELECT * FROM platform  WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;


-- log
create table event_log(
	log_id					SERIAL primary key,
	log_date				date default CURRENT_TIMESTAMP,
	product_id				integer references product (id),
	event 					text
);

CREATE OR REPLACE FUNCTION event_log_insert() RETURNS TRIGGER AS $$
    BEGIN       
            INSERT INTO event_log(product_id,event) values (new.id,TG_OP);
       	    return null;
        
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_audit
AFTER INSERT OR UPDATE OR DELETE ON product
    FOR EACH ROW EXECUTE PROCEDURE event_log_insert();