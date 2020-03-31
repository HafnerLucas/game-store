-- CONSTRUCTORES y DESTRUCTORES
CREATE OR REPLACE FUNCTION product (
	IN p_description                 text,
	IN p_price                       float,
	IN p_quantity                    integer,
	IN p_category                    integer,
    IN p_platform                    integer
) RETURNS product AS
$$
	INSERT INTO Product(description, price, quantity,category_id,platform_id)
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
	IN p_description                      text
) RETURNS text AS
$$ 
	SELECT description FROM Product WHERE description = p_description;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_description (
	IN p_description                      text,
	IN p_new_description                  text
) RETURNS void AS 
$$
	UPDATE Product SET description = p_new_description WHERE description = p_description;
$$ LANGUAGE sql VOLATILE STRICT;


-- price
CREATE OR REPLACE FUNCTION product_get_price (
	IN p_price                      float
) RETURNS float AS
$$ 
	SELECT price FROM Product WHERE price = p_price;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_price (
	IN p_price                      float,
	IN p_new_price                  float
) RETURNS void AS 
$$
	UPDATE Product SET price = p_new_price WHERE price = p_price;
$$ LANGUAGE sql VOLATILE STRICT;


-- quantity
CREATE OR REPLACE FUNCTION product_get_quantity (
	IN p_quantity                      integer
) RETURNS integer AS
$$ 
	SELECT quantity FROM Product WHERE quantity = p_quantity;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_quantity (
	IN p_quantity                      integer,
	IN p_new_quantity                  integer
) RETURNS void AS 
$$
	UPDATE Product SET quantity = p_new_quantity WHERE quantity = p_quantity;
$$ LANGUAGE sql VOLATILE STRICT;


--category
CREATE OR REPLACE FUNCTION product_get_category (
	IN p_category                      integer
) RETURNS integer AS
$$ 
	SELECT 
	 FROM Product WHERE category = p_category;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_category (
	IN p_category                      integer,
	IN p_new_category                  integer
) RETURNS void AS 
$$
	UPDATE Product SET category = p_new_category WHERE category = p_category;
$$ LANGUAGE sql VOLATILE STRICT;


--platform
CREATE OR REPLACE FUNCTION product_get_platform (
	IN p_platform                      integer
) RETURNS integer AS
$$ 
	SELECT 
	 FROM Product WHERE platform = p_platform;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_set_platform (
	IN p_platform                      integer,
	IN p_new_platform                  integer
) RETURNS void AS 
$$
	UPDATE Product SET platform = p_new_platform WHERE platform = p_platform;
$$ LANGUAGE sql VOLATILE STRICT;


-- IDENTIFICACION Y BUSQUEDA
CREATE OR REPLACE FUNCTION product_search (
	IN p_category                  integer DEFAULT '%'
) RETURNS SETOF product AS 
$$
	SELECT * FROM Product 
		WHERE unaccent(category) ilike unaccent('%' || p_category || '%');
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION product_identify (
	IN p_id                     integer
) RETURNS product AS 
$$
	SELECT * FROM Product  WHERE id = p_id;
$$ LANGUAGE sql STABLE STRICT;
