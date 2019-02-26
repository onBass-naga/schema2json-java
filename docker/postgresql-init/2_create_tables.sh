
#!/bin/bash
psql -U root -d test_db << "EOSQL"

create table public.users
(
  id         bigint       not null,
  account    varchar(255) not null,
  last_name  varchar(255) not null,
  first_name varchar(255) not null,
  created_at timestamp    not null,
  primary key (id)
);

create table public.roles
(
  id   bigint       not null,
  name varchar(255) not null,
  primary key (id)
);

create table public.permissions
(
  id   bigint       not null,
  name varchar(255) not null,
  primary key (id)
);

create table public.user_roles
(
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

create table public.role_permissions
(
  role_id       bigint not null,
  permission_id bigint not null,
  primary key (role_id, permission_id),
  foreign key (role_id) references roles (id),
  foreign key (permission_id) references permissions (id)
);

create table public.issues
(
  id          bigint       not null,
  user_id     bigint       not null,
  subject     varchar(255) not null,
  description varchar(255) not null,
  created_at  timestamp    not null,
  primary key (id),
  foreign key (user_id) references users (id)
);

create table myschema.users
(
  id         bigint       not null,
  account    varchar(255) not null,
  last_name  varchar(255) not null,
  first_name varchar(255) not null,
  created_at timestamp    not null,
  primary key (id)
);

create table myschema.roles
(
  id   bigint       not null,
  name varchar(255) not null,
  primary key (id)
);

create table myschema.permissions
(
  id   bigint       not null,
  name varchar(255) not null,
  primary key (id)
);

create table myschema.user_roles
(
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

create table myschema.role_permissions
(
  role_id       bigint not null,
  permission_id bigint not null,
  primary key (role_id, permission_id),
  foreign key (role_id) references roles (id),
  foreign key (permission_id) references permissions (id)
);

create table myschema.issues
(
  id          bigint       not null,
  user_id     bigint       not null,
  subject     varchar(255) not null,
  description varchar(255) not null,
  created_at  timestamp    not null,
  primary key (id),
  foreign key (user_id) references users (id)
);


EOSQL