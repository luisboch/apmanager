CREATE TABLE computer
(
  id serial NOT NULL,
  nome character varying(255) NOT NULL,
  ipv4 character varying(255),
  ipv6 character varying(255),
  active boolean NOT NULL DEFAULT true,
  last_update timestamp(3) without time zone NOT NULL DEFAULT now(),
  creation_date timestamp(3) without time zone NOT NULL DEFAULT now(),
  CONSTRAINT computer_pkey PRIMARY KEY (id)
);


CREATE TABLE users
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  username character varying(255) NOT NULL,
  passwd character varying(255) NOT NULL,
  creation_date timestamp(3) without time zone NOT NULL DEFAULT now(),
  last_update timestamp(3) without time zone NOT NULL DEFAULT now(),
  CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE product_brand (
    id serial primary key,
    "name" character varying(255) NOT NULL,
    creation_date timestamp(3) without time zone NOT NULL default now(),
    description character varying(255),
    last_update timestamp(3) without time zone NOT NULL default now(),
    active boolean not null default true
);


CREATE TABLE shelf (
    id serial primary key,
    description character varying(255) NOT NULL,
    code character varying(255) NOT NULL,
    active boolean not null default true,
    creation_date timestamp(3) not null default now(),
    last_update timestamp(3) not null default now()
);

CREATE TABLE product (
    id serial primary key,
    "name" character varying(255) not null,
    additional_code character varying(255),
    barcode character varying(255) NOT NULL,
    code character varying(255),
    description character varying(255),
    percentual_desc_max integer not null default 0,
    min_quantity integer NOT NULL,
    purchase_price double precision NOT NULL,
    quantity integer NOT NULL,
    sell_price double precision NOT NULL,
    brand_id integer not null,
    shelf_id integer NOT NULL,
    active boolean not null default true,
    creation_date timestamp(3) not null default now(),
    last_update timestamp(3) not null default now(),
    constraint fk_prdt_brand foreign key (brand_id)
	references product_brand(id),
    constraint fk_prdt_shelf foreign key(shelf_id)
	references shelf(id)
);


CREATE TABLE product_keyword (
    id serial primary key,
    keywork character varying(255) NOT NULL,
    product_id bigint NOT NULL,
    constraint fk_keyw_prdt foreign key (product_id)
	references product(id)
);

CREATE TABLE sale (
    id serial primary key,
    canceled boolean NOT NULL default false,
    closed boolean NOT NULL default false,
    close_date timestamp(3) NOT NULL default now(),
    open_date timestamp(3) NOT NULL default now(),
    total double precision default 0.0,
    computer_id integer not null,
    status boolean not null default true,
    creation_date timestamp(3) not null default now(),
    last_update timestamp(3) not null default now(),
    constraint fk_sale_computer foreign key (computer_id)
	references computer(id)
);


CREATE TABLE sale_product (
    id serial primary key,
    purchase_price double precision NOT NULL,
    quantity integer NOT NULL,
    sell_price double precision NOT NULL,
    product_id bigint NOT NULL,
    sale_id bigint NOT NULL,
    status boolean not null default true,
    creation_date timestamp(3) not null default now(),
    last_update timestamp(3) not null default now(),
    constraint fk_sale_prdt foreign key (product_id)
	references product(id)
);

