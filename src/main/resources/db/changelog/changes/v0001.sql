create table "person" (
  id bigserial not null,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  date_of_birth date not null,
  address varchar(250) default null,
  city varchar(250) default null,
  zip_code varchar(10) default null,
  primary key (id)
);
