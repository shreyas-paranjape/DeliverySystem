-- DDL
drop schema public cascade;
create schema public;
create extension ltree;
----------------------------------------------------------	PARTY	-------------------------------------------------------------

create table postal_address
(
	id serial primary key,
	addressline1 text,
	addressline2 text
);

create table party_identity
(
	id integer primary key
);

create or replace function party_identity_fn
() RETURNS trigger AS
$BODY$
DECLARE
BEGIN
	IF (TG_OP = 'INSERT') THEN
		insert into party_identity(id) values (NEW.id);
		RETURN NEW;
	ELSIF (TG_OP = 'DELETE') THEN
		delete from party_identity where id = OLD.id;
		RETURN OLD;
	ELSIF (TG_OP = 'UPDATE' and OLD.id != NEW.id) THEN
		update party_identity
			set id = NEW.id
			where id = OLD.id;
		return NEW;
	END IF;

END
$BODY$
LANGUAGE plpgsql VOLATILE COST 100;

CREATE SEQUENCE party_id_sequence;

create table party
(
	id integer primary key references party_identity(id) DEFAULT nextval('party_id_sequence'),
	address_id integer NOT NULL references postal_address(id)
);

CREATE TRIGGER party_trigger
	BEFORE INSERT OR UPDATE OR DELETE
	ON party
	FOR EACH ROW
	EXECUTE PROCEDURE party_identity_fn();

create table org
(
	name text,
	CONSTRAINT fk_org_address
		FOREIGN KEY (address_id) REFERENCES postal_address (id)

) inherits(party);

CREATE TRIGGER org_trigger
BEFORE INSERT OR UPDATE OR DELETE
ON org
FOR EACH ROW
EXECUTE PROCEDURE party_identity_fn();


create table person
(
	last_name text,
	first_name text,
	gender CHAR(1),
	birth_date DATE
	CONSTRAINT fk_person_address
		FOREIGN KEY (address_id) REFERENCES postal_address (id)


) inherits(party);

CREATE TRIGGER person_trigger
BEFORE INSERT OR UPDATE OR DELETE
ON person
FOR EACH ROW
EXECUTE PROCEDURE party_identity_fn();


-- ROLES
-- Customer,Employee, Vendor_Contact
create table role_type
(
	id serial primary key,
	description text
);

create table party_role_identity
(
	id integer primary key
);

create or replace function party_role_identity_fn
() RETURNS trigger AS
$BODY$
DECLARE
BEGIN
	IF (TG_OP = 'INSERT') THEN
		IF(TG_TABLE_NAME = 'person_role') THEN
			IF(select count(*) = 1 from person where id = NEW.person_id) THEN
				insert into party_role_identity(id) values (NEW.id);
				RETURN NEW;
			ELSE
				RETURN NULL;
			END IF;
		ELSIF(TG_TABLE_NAME = 'org_role') THEN
			IF(select count(*) = 1 from org where id = NEW.org_id) THEN
				insert into party_role_identity(id) values (NEW.id);
				RETURN NEW;
			ELSE
				RETURN NULL;
			END IF;
		END IF;
	ELSIF (TG_OP = 'DELETE') THEN
		delete from party_role_identity where id = OLD.id;
		RETURN OLD;
	ELSIF (TG_OP = 'UPDATE' and OLD.id != NEW.id) THEN
		update party_role_identity
			set id = NEW.id
			where id = OLD.id;
		return NEW;
	END IF;
END
$BODY$
LANGUAGE plpgsql VOLATILE COST 100;

CREATE SEQUENCE party_role_id_sequence;

create table party_role
(
	id integer NOT NULL references party_role_identity(id) DEFAULT nextval('party_role_id_sequence'),
	from_date date,
	through_date date
);

CREATE TRIGGER party_role_trigger
BEFORE INSERT OR UPDATE OR DELETE
ON party_role
FOR EACH ROW
EXECUTE PROCEDURE party_role_identity_fn();

create table person_role
(
	person_id integer references party_identity(id),
	role_type_id integer references role_type(id)

) INHERITS(party_role);

CREATE TRIGGER person_role_trigger
BEFORE INSERT OR UPDATE OR DELETE
ON person_role
FOR EACH ROW
EXECUTE PROCEDURE party_role_identity_fn();

create table org_role
(
	org_id integer references party_identity(id),
	role_type_id integer references role_type(id)

) INHERITS(party_role);

CREATE TRIGGER org_role_trigger
BEFORE INSERT OR UPDATE OR DELETE
ON org_role
FOR EACH ROW
EXECUTE PROCEDURE party_role_identity_fn();

----------------------------------------------------------	PRODUCTS	-------------------------------------------------------------

create table product_identity
(
	id integer primary key
);

CREATE SEQUENCE product_identity_sequence;


create table product
(
	id integer NOT NULL references product_identity(id) DEFAULT nextval('product_identity_sequence'),
	name text,
	intro_date date,
	discont_date date
);

create table good
(

) inherits(product);


create table service
(

) inherits(product);


create table product_category
(
	id integer,
	name text,
	parent_id integer,
);


create table product_category_rollup
(
	product_category_id integer,
	lft integer,
	rgt integer
);

create table product_category_classification
(
	product_id integer refrences product_identity(id),
	product_category_id integer references product_category(id),
	from_date date,
	to_date date
);




-------------------------------------------------		ORDERS		-------------------------------------------------------------











--------------------------------------------------		FULFILLMENT	---------------------------------------------------------------
