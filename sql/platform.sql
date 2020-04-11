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


