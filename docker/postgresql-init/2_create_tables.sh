
#!/bin/bash
psql -U root -d test_db << "EOSQL"

create table persons
(
  id    int8         not null,
  email varchar(255) not null,
  name  varchar(255) not null,
  primary key (id)
);

create table purchase
(
  id             int8 not null,
  address        varchar(255),
  email          varchar(255),
  gift_wrapping  varchar(255),
  name           varchar(255),
  payment_method varchar(255),
  prefecture     int4,
  tel            varchar(255),
  primary key (id)
);

CREATE TABLE users (
  id serial PRIMARY KEY,
  name varchar(256)
);

CREATE TABLE cars (
  id serial,
  user_id int REFERENCES users(id) ON UPDATE CASCADE ON DELETE SET NULL
);

EOSQL