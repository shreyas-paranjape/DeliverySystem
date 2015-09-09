DROP SCHEMA public cascade;
CREATE SCHEMA public;
create extension ltree;

CREATE TABLE public.base_table
(
  description text,
  created_stamp timestamp,
  last_updated_stamp timestamp
);

CREATE TABLE public.dated_table
(
  from_date timestamp,
  through_date timestamp
);

------------------------------------------------------COMMON------------------------------------------------------------

CREATE TABLE public.status_type
(
  id serial primary key

)inherits(public.base_table);


CREATE TABLE public.geo_point
(
  id serial primary key,
  latitude float not null,
  longitude float not null,
  unique(latitude,longitude)

)inherits(public.base_table);

-- postal,tele,electronic etc.. hierarchial
CREATE TABLE public.contact_mech_type
(
  id serial primary key,
  node_path ltree

)inherits(public.base_table);

CREATE INDEX path_gist_contact_mech_type_idx ON public.contact_mech_type USING GIST(node_path);
CREATE INDEX path_contact_mech_type_idx ON public.contact_mech_type USING btree(node_path);


CREATE TABLE public.contact_mech
(
  id serial primary key,
  contact_mech_type_id integer references public.contact_mech_type

)inherits(public.base_table);

-- Main phone, Office phone etc..
CREATE TABLE public.contact_mech_purpose_type
(
  id serial primary key

)inherits(public.base_table);


-- Party roles, Site roles etc..
CREATE TABLE public.role_type
(
  id serial primary key,
  node_path ltree

)inherits(public.base_table);

CREATE INDEX path_gist_role_type_idx ON public.role_type USING GIST(node_path);
CREATE INDEX path_role_type_idx ON public.role_type USING btree(node_path);

CREATE TABLE public.relationship_type
(
  id serial primary key,
  from_role_type_id integer references public.role_type,
  to_role_type_id integer references public.role_type,
  node_path ltree

)inherits(public.base_table);

CREATE INDEX path_gist_relationship_type_idx ON public.relationship_type USING GIST(node_path);
CREATE INDEX path_relationship_type_idx ON public.relationship_type USING btree(node_path);

------------------------------------------------------------ PARTY ---------------------------------------------------------------------------



CREATE TABLE public.party_type
(
  id serial primary key

)inherits(public.base_table);

CREATE TABLE public.party_class_type
(
  id serial primary key

)inherits(public.base_table);

CREATE TABLE public.party_class_group
(
  id serial primary key,
  party_class_type_id integer references public.party_class_type,
  node_path ltree

)inherits(public.base_table);

CREATE INDEX path_gist_party_class_group_idx ON public.party_class_group USING GIST(node_path);
CREATE INDEX path_party_class_group_idx ON public.party_class_group USING btree(node_path);

CREATE TABLE public.party
(
  id serial primary key,
  party_type_id integer references party_type

)inherits(public.base_table);


CREATE TABLE public.party_attribute
(
  party_id integer references public.party,
  attr_name varchar(60),
  primary key (party_id,attr_name)

)inherits(public.base_table);


CREATE TABLE public.party_class
(
  party_id integer references public.party(id),
  party_class_group_id integer references public.party_class_group,
  primary key(party_id,party_class_group_id,from_date)

)inherits(public.base_table,public.dated_table);


CREATE TABLE public.party_contact_mech
(
  party_id integer references public.party,
  contact_mech_id integer references public.contact_mech,
  primary key(party_id,contact_mech_id,from_date)

)inherits(public.base_table,public.dated_table);


CREATE TABLE public.party_contact_purpose
(
  party_id integer references public.party,
  contact_mech_purpose_type_id integer references public.contact_mech_purpose_type,
  primary key(party_id,contact_mech_purpose_type_id,from_date)

)inherits(public.base_table,public.dated_table);


CREATE TABLE public.party_role
(
  party_id integer references public.party,
  role_type_id integer references public.role_type,
  primary key(party_id,role_type_id)

)inherits(public.base_table);

CREATE TABLE public.party_relationship
(
  party_id integer references public.party,
  relationship_type_id integer references public.relationship_type,
  primary key(party_id,relationship_type_id,from_date)

)inherits(public.base_table,public.dated_table);

---------------------------------------------------------------SITE----------------------------------------------------

CREATE TABLE public.site_type
(
  id serial primary key,
  node_path ltree

)inherits(public.base_table);


CREATE INDEX path_gist_site_type_idx ON public.site_type USING GIST(node_path);
CREATE INDEX path_site_type_idx ON public.site_type USING btree(node_path);

CREATE TABLE public.site
(
  id serial primary key,
  geo_point_id integer references public.geo_point,
  site_type_id integer references public.site_type

)inherits (public.base_table);

CREATE TABLE public.site_attribute
(
  site_id integer references public.site(id),
  attr_name varchar(60),
  primary key (site_id,attr_name)

)inherits(public.base_table);


CREATE TABLE public.party_site
(
  party_id integer references public.party,
  site_id integer references public.site,
  role_type_id integer references public.role_type,
  primary key (party_id,site_id,role_type_id)

)inherits(public.base_table,public.dated_table);


---------------------------------------------------------------PRODUCT----------------------------------------------------
CREATE TABLE public.product_category
(
  id serial primary key,
  node_path ltree

)inherits(public.base_table);

CREATE INDEX path_gist_product_category_idx ON public.product_category USING GIST(node_path);
CREATE INDEX path_product_category_idx ON public.product_category USING btree(node_path);


CREATE TABLE public.product
(
  id serial primary key,
  product_category_id integer references public.product_category

)inherits(public.base_table);

CREATE TABLE public.product_attribute
(
  product_id integer references public.product(id),
  attr_name varchar(60),
  primary key (product_id,attr_name)

)inherits(public.base_table);


---------------------------------------------------------------INVENTORY----------------------------------------------------

CREATE TYPE recurrence_frequency AS ENUM ('HOURLY', 'DAILY', 'WEEKLY', 'MONTHLY','YEARLY');


/* CREATE TABLE recurrence_rule
(
 id serial primary key,
 frequency recurrence_frequency,
 by_minute integer[],
 by_hour integer[],
 by_day_of_week integer[],
 by_day_of_month integer[],
 by_day_of_year integer[],
 by_week_of_month integer[],
 by_week_of_year integer[],
 by_month_of_year inetger[]

)inherits(public.base_table); */
