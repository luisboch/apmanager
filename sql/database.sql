CREATE TABLE computer
(
  id serial NOT NULL,
  nome character varying(255) NOT NULL,
  ipv4 character varying(255),
  ipv6 character varying(255),
  status boolean NOT NULL DEFAULT true,
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
