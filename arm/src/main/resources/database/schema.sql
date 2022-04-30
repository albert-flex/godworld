-- User
drop table if exists user;
create table user (
    id bigint not null auto_increment,
    user_name varchar(30) not null unique,
    password varchar(30) not null default 'U2FsdGVkX1/vejp2/xOSw+RzSM4YDQ==',
    enabled boolean not null default true,
    login boolean not null default false,
    create_time datetime not null default now(),
    last_login_time datetime,
    primary key(id)
);

drop table if exists u_group;
create table u_group(
    id bigint not null auto_increment,
    name varchar(30) not null unique,
    description varchar(256) not null,
    primary key(id)
);

drop table if exists permission;
create table permission(
    id bigint not null auto_increment,
    name varchar(30) not null,
    description varchar(256) not null,
    primary key(id)
);

drop table if exists user_group;
create table user_group(
    id bigint not null auto_increment,
    user_id bigint not null,
    group_id bigint not null,
    primary key(id)
);

drop table if exists group_permission;
create table group_permission(
    id bigint not null auto_increment,
    group_id bigint not null,
    permission_id bigint not null,
    primary key(id)
);
