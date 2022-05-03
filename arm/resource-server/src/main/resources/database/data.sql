/**
 * User Group Permission
 */
delete from permission;
insert into permission(id,name,description) values
(1,"AddBook","添加Book"),
(2,"EditBook","更新Book"),
(3,"RemoveBook","移除Book"),
(4,"ViewBook","观看Book");

delete from u_group;
insert into u_group(id,name,description) values
(1,"Admin","管理员"),
(2,"Reader","读者"),
(3,"Author","作者");

delete from user;
insert into user(id,user_name) values
(1,"A1"),
(2,"A2"),
(3,"A3");

delete from group_permission;
insert into group_permission(id,group_id,permission_id) values
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,2,4),
(6,3,1),
(7,3,2),
(8,3,3),
(9,3,4);

delete from user_group;
insert into user_group(id,user_id,group_id) values
(1,1,1),
(2,1,2),
(3,1,3),
(4,2,2),
(5,3,3);


/**
 * Book
 */

delete from book_board;
insert book_board(id,name) values
(1,"科幻"),
(2,"言情"),
(3,"校园"),
(4,"奇幻"),
(9,"玄幻"),
(5,"都市"),
(6,"历史"),
(7,"随笔"),
(8,"散文");

 delete from book_info;
 insert book_info(id,author_name,name,board_name) values
 (1,"夏文纯一","樱花变奏曲","校园"),
 (2,"夏文纯一","谋杀者Z","校园"),
 (3,"夏文纯一","被封印的魔力","奇幻"),
 (4,"夏文纯一","无界法魂","玄幻"),
 (5,"夏文纯一","康德莱特街39号","校园"),
 (6,"阿尔伯特","Spring EE的开发方式","随笔");


/**
 * Author
 */
delete from author_info;
insert author_info(id,name,user_id,email,moto) values
(1,"夏文纯一",1,"natsufumij@yeah.net","恭喜你，尤克，从今后你就是A级冒险者了"),
(2,"阿尔伯特",2,"albert_flex@yeah.net","恭喜你，尤克，从今后你就是A级冒险者了");