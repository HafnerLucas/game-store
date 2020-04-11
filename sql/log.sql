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