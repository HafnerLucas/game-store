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
