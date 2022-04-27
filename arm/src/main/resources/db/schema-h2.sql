
-- User
drop table if exists user;
create table user (
    id bigint not null auto_increment,
    user_name varchar(30) not null unique,
    password varchar(30) not null default 'U2FsdGVkX1/vejp2/xOSw+RzSM4YDQ==',
    enable boolean not null default true,
    login boolean not null default false,
    create_time datetime not null default now(),
    last_login_time datetime,
    primary key(id)
);

drop table if exists group;
create table group(
    id bigint not null auto_increment,
    name varchar(30) not null unique,
    description varchar(256) not null,
    primary key(id)
);

drop table if exists permission;
create table permission(
    id bigint not null auto_increment,
    user_name varchar(30) not null,
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


-- Point Score
drop table if exists point_score;
create table point_score(
    id bigint not null auto_increment,
    user_id bigint not null,
    point bigint not null,
    primary key(id)
);

drop table if exists point_order;
create table point_order(
    id bigint not null auto_increment,
    source_id bigint not null,
    target_id bigint not null,
    order_score bigint not null,
    type int not null,
    state int not null,
    back varchar(256) not null,
    failed_word varchar(256) not null,
    create_time datetime not null,
    handle_time datetime not null,
    primary key(id)
);

drop table if exists point_record;
create table point_record(
    id bigint not null auto_increment,
    source_id bigint not null,
    target_id bigint not null,
    out_come bigint not null,
    order_id bigint not null,
    type int not null,
    detail varchar(256) not null,
    time datetime not null,
    primary key(id)
);


-- Book
drop table if exists book_info;
create table book_info(
    id bigint not null auto_increment,
    author_id bigint not null,
    click_count bigint not null,
    board_id bigint not null,
    title varchar(256) not null,
    description varchar(256) not null,
    up_shelf boolean not null default false,
    create_time datetime not null default now(),
    update_time datetime not null default now(),
    update_title varchar(256) not null default '',
    new_chapter_id bigint not null,
    primary key(id)
);

drop table if exists book_board;
create table book_board(
    id bigint not null auto_increment,
    name varchar(30) not null,
    description varchar(256) not null,
    primary key(id)
);

drop table if exists book_part;
create table book_part(
    id bigint not null auto_increment,
    book_id bigint not null,
    name varchar(30) not null,
    primary key(id)
);

drop table if exists book_volume;
create table book_volume(
    id bigint not null auto_increment,
    book_id bigint not null,
    part_id bigint not null,
    name varchar(30) not null,
    primary key(id)
);

drop table if exists book_chapter;
create table book_chapter(
    id bigint not null auto_increment,
    book_id bigint not null,
    part_id bigint not null,
    volume_id bigint not null,
    name varchar(30) not null,
    create_time datetime not null default now(),
    update_time datetime not null default now(),
    primary key(id)
);

drop table if exists book_tag;
create table book_tag(
    id bigint not null auto_increment,
    name varchar(30) not null,
    detail varchar(256) not null,
    publish_time datetime not null default now(),
    primary key(id)
);

drop table if exists book_tag_bind;
create table book_tag_bind(
    id bigint not null auto_increment,
    book_id bigint not null,
    tag_id bigint not null,
    primary key(id)
);

drop table if exists book_comment;
create table book_comment(
    id bigint not null auto_increment,
    book_id bigint not null,
    reader_id bigint not null,
    favor boolean not null default false,
    content varchar(256) not null,
    time datetime not null default now(),
    like bigint not null default 0,
    primary key(id)
);

drop table if exists book_resource;
create table book_resource(
    id bigint not null auto_increment,
    book_id bigint not null,
    chapter_id bigint not null,
    res_id bigint not null,
    publish_time datetime not null default now(),
    primary key(id)
);

drop table if exists book_hot;
create table book_hot(
    id bigint not null auto_increment,
    hot_day datetime not null,
    order int not null default 0,
    hot_score bigint not null default 0,
    book_id bigint not null,
    author_id bigint not null,
    primary key(id)
);


-- File
drop table if exists file_resource;
create table file_resource(
    id bigint not null auto_increment,
    format varchar(30) not null,
    lib varchar(30) not null,
    name varchar(256) not null,
    primary key(id)
);


-- Notify
drop table if exists notify_info;
create table notify_info(
    id bigint not null auto_increment,
    source int not null,
    source_id bigint not null,
    target int not null,
    target_id bigint not null,
    content varchar(256) not null,
    time datetime not null,
    read boolean not null default false,
    primary key(id)
);


-- Social
drop table if exists social_info;
create table social_info(
    id bigint not null auto_increment,
    master_author_id bigint not null,
    member_number bigint not null,
    top_activity_id bigint not null,
    locked boolean not null default false,
    name varchar(30) not null,
    description varchar(256) not null,
    create_time datetime not null,
    primary key(id)
);

drop table if exists social_activity;
create table social_activity(
    id bigint not null auto_increment,
    social_id bigint not null,
    honer_member_id bigint not null,
    contact_member_id bigint not null,
    name varchar(30) not null,
    description varchar(256) not null,
    start_time datetime not null,
    end_time datetime not null,
    state int not null,
    primary key(id)
);

drop table if exists social_member;
create table social_member(
    id bigint not null auto_increment,
    social_id bigint not null,
    author_id bigint not null,
    time_in bigint not null,
    type int not null,
    primary key(id)
);

drop table social_hot;
create table social_hot(
    id bigint not null auto_increment,
    hot_day datetime not null,
    order int not null,
    hot_score bigint not null,
    social_id bigint not null,
    primary key(id)
);

drop table social_request;
create table social_request(
    id bigint not null auto_increment,
    content varchar(256) not null,
    social_id bigint not null,
    type int not null,
    request_author_id bigint not null,
    dest_id bigint not null,
    state int not null,
    handler_id bigint not null,
    time datetime not null,
    handle_time datetime not null,
    primary key(id)
);

drop table if exists social_record;
create table social_record(
    id bigint not null auto_increment,
    social_id bigint not null,
    type int not null,
    content varchar(256) not null,
    time datetime not null,
    primary key(id)
);


-- Reader
drop table if exists reader_info;
create table reader_info(
    id bigint not null auto_increment,
    user_id bigint not null,
    name varchar(30) not null,
    locked boolean not null false,
    not_word boolean not null false,
    phone varchar(30) not null,
    email varchar(30) not null,
    moto varchar(256) not null default '',
    primary key(id)
);

drop table if exists reader_owned_book;
create table reader_owned_book(
    id bigint not null auto_increment,
    reader_id bigint not null,
    book_id bigint not null,
    primary key(id)
);

drop table if exists reader_read_record;
create table reader_read_record(
    id bigint not null auto_increment,
    reader_id bigint not null,
    book_id bigint not null,
    chapter_id bigint not null,
    time datetime not null,
    primary key(id)
);

drop table if exists reader_shelf_book;
create table reader_shelf_book(
    id bigint not null auto_increment,
    reader_id bigint not null,
    book_id bigint not null,
    order int not null,
    primary key(id)
);

drop table if exists reader_starred_book;
create table reader_starred_book(
    id bigint not null auto_increment,
    reader_id bigint not null,
    book_id bigint not null,
    time datetime not null,
    primary key(id)
);

-- Author
drop table if exists author_info;
create table author_info(
    id bigint not null auto_increment,
    user_id bigint not null,
    locked boolean not null default false,
    pen_name varchar(30) not null,
    phone varchar(30) not null,
    email varchar(30) not null,
    moto varchar(256) not null,
    address varchar(256) not null,
    link_time datetime not null,
    primary key(id)
);

drop table if exists author_record;
create table author_record(
    id bigint not null auto_increment,
    author_id bigint not null,
    type int not null,
    dest_id bigint not null,
    description varchar(256) not null,
    time datetime not null,
    primary key(id)
);

