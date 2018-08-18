
create table T_INTERFACE_CASE (
	id integer not null primary key,
	name varchar(80),
	url varchar(1024),
	match_rule varchar(4096),
	response varchar(10240),
	content_type varchar(64),
	status integer,
	create_time timestamp,
	update_time timestamp
);

CREATE SEQUENCE INTERFACE_CASE_ID
AS INT
START WITH 1
INCREMENT BY 1
MAXVALUE 99999999
NO CYCLE;
