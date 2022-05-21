/**
 * User
 */
drop table if exists user;
create table user (
    id bigint not null auto_increment,
    username varchar(30) not null unique,
    password varchar(512) not null default '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq',
    email varchar(30) not null unique,
    locked boolean not null default false,
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

/**
 * Book
 */

drop table if exists book_board;
create table book_board(
    id bigint not null auto_increment,
    name varchar(30) not null unique,
    primary key(id)
);

drop table if exists book_info;
create table book_info(
    id bigint not null auto_increment,
    author_id bigint not null,
    name varchar(30) not null unique,
    point bigint not null default 0,
    word_count bigint not null default 0,
    description varchar(512) not null default '',
    board_id bigint not null,
    create_time datetime not null default now(),
    update_time datetime not null default now(),
    update_chapter_id bigint not null default 0,
    primary key(id)
);

drop table if exists book_volume;
create table book_volume(
    id bigint not null auto_increment,
    book_id bigint not null,
    name varchar(30) not null default "未命名" unique,
    prev_volume_id bigint not null default 0,
    next_volume_id bigint not null default 0,
    primary key(id)
);

drop table if exists book_chapter;
create table book_chapter(
    id bigint not null auto_increment,
    book_id bigint not null,
    volume_id bigint not null,
    title varchar(30) not null default "未命名" unique,
    content text not null,
    create_time datetime not null default now(),
    update_time datetime not null default now(),
    prev_chapter_id bigint not null default 0,
    next_chapter_id bigint not null default 0,
    primary key(id)
);

drop table if exists book_comment;
create table book_comment(
    id bigint not null auto_increment,
    book_id bigint not null,
    user_id bigint not null,
    content varchar(1024) not null default "无内容",
    create_time datetime not null default now(),
    primary key(id)
);

drop table if exists book_res;
create table book_res(
    id bigint not null auto_increment,
    book_id bigint not null,
    create_time datetime not null,
    title varchar(30) not null,
    res_id bigint not null,
    primary key(id)
);

drop table if exists book_shelf_book;
create table book_shelf_book(
    id bigint not null auto_increment,
    user_id bigint not null,
    book_id bigint not null,
    add_time datetime not null default now(),
    primary key(id)
);

drop table if exists book_tag;
create table book_tag(
    id bigint not null auto_increment,
    name varchar(30) not null unique,
    primary key(id)
);

drop table if exists book_tag_bind;
create table book_tag_bind(
    id bigint not null auto_increment,
    book_id bigint not null,
    tag_id bigint not null,
    primary key(id)
);

/**
 * Author
 */
drop table if exists author_info;
create table author_info(
    id bigint not null auto_increment,
    user_id bigint not null,
    name varchar(30) not null unique,
    email varchar(30) not null,
    moto varchar(1024) not null default '',
    present_book_id bigint not null default 0,
    create_time datetime not null default now(),
    primary key(id)
);


/**
 * File Resource
 */
drop table if exists file_resource;
create table file_resource(
    id bigint not null auto_increment,
    name varchar(256) not null unique,
    format varchar(30) not null,
    lib varchar(256) not null,
    own_id bigint not null,
    primary key(id)
);

/**
 * Social
 */
drop table if exists social_info;
create table social_info(
    id bigint not null auto_increment,
    name varchar(30) not null unique,
    master_id bigint not null,
    moto varchar(256) not null default 'moto',
    create_time datetime not null default now(),
    primary key(id)
);

drop table if exists social_member;
create table social_member(
    id bigint not null auto_increment,
    social_id bigint not null,
    author_id bigint not null,
    member_name varchar(30) not null unique,
    type varchar(64) not null,
    in_time datetime not null default now(),
    primary key(id)
);

drop table if exists social_announce;
create table social_announce(
    id bigint not null auto_increment,
    social_id bigint not null,
    publish_author_id bigint not null,
    title varchar(30) not null,
    content text not null,
    publish_time datetime not null default now(),
    update_time datetime not null default now(),
    primary key(id)
);

drop table if exists social_activity;
create table social_activity(
    id bigint not null auto_increment,
    social_id bigint not null,
    honer_author_id bigint not null,
    name varchar(30) not null unique,
    description varchar(256) not null,
    start_time datetime not null,
    end_time datetime not null,
    act_on boolean not null default false,
    primary key(id)
);

drop table if exists social_activity_book;
create table social_activity_book(
    id bigint not null auto_increment,
    social_id bigint not null,
    activity_id bigint not null,
    book_id bigint not null,
    primary key(id)
);