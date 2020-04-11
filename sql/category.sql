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


