
create table api_query(
	id bigserial primary key,
	name varchar(20),
	contents varchar(1000),
	description varchar(300),
	resourcer_id int,
	created TIMESTAMPTZ,
	deleted TIMESTAMPTZ,
	is_enabled bool
);
create index api_query_resourcer on api_query(resourcer_id);

create table api_query_parameter(
	id bigserial primary key,
	name varchar(20),
	description varchar(300),
	io_type varchar(8),
	is_optional bool,
	query_id int,
	created TIMESTAMPTZ,
	deleted TIMESTAMPTZ,
	is_enabled bool
);
create index api_query_parameter_query_id on api_query_parameter(query_id);

create table api_resourcer_info(
	id bigserial primary key,
	name varchar(20),
	path varchar(300) unique,
	description varchar(300),
	access_name varchar(20),
	key varchar(128),
	driver varchar(20),
	driver_class_name varchar(100),
	created TIMESTAMPTZ,
	deleted TIMESTAMPTZ,
	is_enabled bool
);

create table api_router(
	id bigserial primary key,
	name varchar(20),
	path varchar(300) unique,
	description varchar(300),
	is_disabled bool,
	query_id int,
	created TIMESTAMPTZ,
	deleted TIMESTAMPTZ,
	is_enabled bool
);

create table api_resource_request(
	id bigserial primary key,
	requester_id int,
	router_id int,
	state int,
	created TIMESTAMPTZ,
	deleted TIMESTAMPTZ,
	is_enabled bool
);

create table api_query_argument(
	id bigserial primary key,
	query_parameter_id int,
	request_history_id int,
	contents varchar(300),
	created TIMESTAMPTZ,
	deleted TIMESTAMPTZ,
	is_enabled bool
);

drop table api_router ;
drop table api_query ;
drop table api_query_parameter; 
drop table api_resourcer_info ;
drop table api_resource_request;
drop table api_query_argument;


select * from api_resourcer_info ari;
delete from api_resourcer_info;


select
        r1_0.id,
        r1_0.access_name,
        r1_0.created,
        r1_0.deleted,
        r1_0.description,
        r1_0.driver,
        r1_0.driver_class_name,
        r1_0.is_enabled,
        r1_0.key,
        r1_0.name,
        r1_0.path
    from
        api_resourcer_info r1_0


         select
        r1_0.id,
        r1_0.access_name,
        r1_0.created,
        r1_0.deleted,
        r1_0.description,
        r1_0.driver,
        r1_0.driver_class_name,
        r1_0.is_enabled,
        r1_0.key,
        r1_0.name,
        r1_0.path
    from
        api_resourcer_info r1_0
    where
        r1_0.id=2
    
        
select * from api_query aq;




select q1_0.query_id,q1_1.id,q1_1.created,q1_1.deleted,q1_1.description,q1_1.io_type,q1_1.is_enabled,q1_1.is_optional,q1_1.name,q2_0.id,q2_0.contents,q2_0.created,q2_0.deleted,q2_0.description,q2_0.is_enabled,q2_0.name,r1_0.id,r1_0.access_name,r1_0.created,r1_0.deleted,r1_0.description,r1_0.driver,r1_0.driver_class_name,r1_0.is_enabled,r1_0.key,r1_0.name,r1_0.path 
from api_query_query_parameters q1_0 
join api_query_parameter q1_1 on q1_1.id=q1_0.query_parameters_id 
left join api_query q2_0 on q2_0.id=q1_1.query_id 
left join api_resourcer_info r1_0 on r1_0.id=q2_0.resourcer_id 
where q1_0.query_id=?

        
    select
        r1_0.id,
        r1_0.created,
        r1_0.deleted,
        r1_0.description,
        r1_0.is_disabled,
        r1_0.is_enabled,
        r1_0.name,
        r1_0.path,
        q1_0.id,
        q1_0.contents,
        q1_0.created,
        q1_0.deleted,
        q1_0.description,
        q1_0.is_enabled,
        q1_0.name,
        r2_0.id,
        r2_0.access_name,
        r2_0.created,
        r2_0.deleted,
        r2_0.description,
        r2_0.driver,
        r2_0.driver_class_name,
        r2_0.is_enabled,
        r2_0.key,
        r2_0.name,
        r2_0.path
    from
        api_router r1_0
    left join
        api_query q1_0
            on q1_0.id=r1_0.query_id
    left join
        api_resourcer_info r2_0
            on r2_0.id=q1_0.resourcer_id
    where
        r1_0.id=1

        
        
        select
        r1_0.id,
        r1_0.created,
        r1_0.deleted,
        r1_0.description,
        r1_0.is_disabled,
        r1_0.is_enabled,
        r1_0.name,
        r1_0.path,
        q1_0.id,
        q1_0.contents,
        q1_0.created,
        q1_0.deleted,
        q1_0.description,
        q1_0.is_enabled,
        q1_0.name,
        r2_0.id,
        r2_0.access_name,
        r2_0.created,
        r2_0.deleted,
        r2_0.description,
        r2_0.driver,
        r2_0.driver_class_name,
        r2_0.is_enabled,
        r2_0.key,
        r2_0.name,
        r2_0.path
    from
        api_router r1_0
    left join
        api_query q1_0
            on q1_0.id=r1_0.query_id
    left join
        api_resourcer_info r2_0
            on r2_0.id=q1_0.resourcer_id
    where
        r1_0.id=2
        
        
 select
        r1_0.id,
        r1_0.created,
        r1_0.deleted,
        r1_0.description,
        r1_0.is_disabled,
        r1_0.is_enabled,
        r1_0.name,
        r1_0.path,
        r1_0.query_id
    from
        api_router r1_0