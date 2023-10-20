
create table api_query(
	id serial primary key,
	name varchar(20),
	contents varchar(1000),
	description varchar(600),
	resourcer_id int
);
create index api_query_resourcer on api_query(resourcer_id);

create table api_query_parameter(
	id serial primary key,
	name varchar(20),
	description varchar(600),
	type varchar(8),
	is_optional bit(1),
	query_id int
);
create index api_query_parameter_query_id on api_query_parameter(query_id);

create table api_resourcer_info(
	id serial primary key,
	name varchar(20),
	path varchar(900) unique,
	description varchar(600),
	key varchar(128)
);

create table api_router(
	id serial primary key,
	name varchar(20),
	path varchar(500) unique,
	description varchar(600),
	is_disabled bit(1),
	query_id int
)



