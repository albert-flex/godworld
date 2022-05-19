/**
 * User Group Permission
 */
delete from permission;
insert into permission(id,name,description) values
(1,"ADMIN_PER","管理员权限"),
(2,"USER_PER","用户权限"),
(3,"AUTHOR_PER","作者权限"),
(4,"SOCIAL_PER","社员权限"),
(5,"SOCIAL_ADMIN_PER","社团管理权限");

delete from u_group;
insert into u_group(id,name,description) values
(1,"Admin","管理员"),
(2,"Reader","读者"),
(3,"Author","作者"),
(4,"Social","社员"),
(5,"SocialAdmin","社团管理员");

delete from user;
insert into user(id,username,email) values
(1,"A1","A1@godworld.com"),
(2,"A2","A2@godworld.com"),
(3,"A3","A3@godworld.com");

delete from group_permission;
insert into group_permission(id,group_id,permission_id) values
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,2,1),
(7,3,2),
(8,4,2),
(9,4,3),
(10,4,4),
(11,5,2),
(12,5,3),
(13,5,4),
(14,5,5);

delete from user_group;
insert into user_group(id,user_id,group_id) values
(1,1,1),
(2,1,2),
(3,1,3),
(4,2,2),
(5,3,3);

/**
 * Author
 */
delete from author_info;
insert author_info(id,name,user_id,email,moto) values
(1,"夏文纯一",1,"natsufumij@yeah.net","恭喜你，尤克，从今后你就是A级冒险者了"),
(2,"阿尔伯特",2,"albert_flex@yeah.net","恭喜你，尤克，从今后你就是A级冒险者了");


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
insert book_info(id,author_id,name,board_id,update_chapter_id) values
(1,1,"樱花变奏曲",3,1),
(2,1,"谋杀者Z",3,0),
(3,1,"被封印的魔力",4,0),
(4,1,"无界法魂",9,0),
(5,1,"康德莱特街39号",3,0),
(6,2,"Spring EE的开发方式",7,0);

delete from book_tag;
insert book_tag(id,name) values
(1,"冒险"),
(2,"魔法"),
(3,"轻小说"),
(4,"治愈"),
(5,"耽美"),
(6,"后宫"),
(7,"魔幻"),
(8,"武侠"),
(9,"侦探"),
(10,"幻想"),
(11,"复仇"),
(12,"推理"),
(13,"技术");

delete from book_tag_bind;
insert book_tag_bind(id,book_id,tag_id) values
(1,1,3),
(2,1,4),
(3,2,3),
(4,2,4),
(5,2,11),
(6,2,12),
(7,3,1),
(8,3,2),
(9,3,3),
(10,3,4),
(11,3,6),
(12,4,1),
(13,4,2),
(14,4,3),
(15,4,4),
(16,4,6),
(17,4,11),
(18,5,1),
(19,5,2),
(20,5,3),
(21,5,4),
(22,5,6),
(23,6,13);

delete from book_volume;
insert into book_volume(id,book_id,name) values
(1,1,'第一卷 嗯，这个名字不错');

delete from book_chapter;
insert into book_chapter(id,book_id,volume_id,title,content,prev_chapter_id,next_chapter_id)values
(1,1,1,'第一章 新生报告会','众所周知，这里是一个十分友好的城市，在这个城市里...',0,3),
(2,1,1,'第三章 可爱的人是谁','众所周知，这里是一个十分友好的城市，在这个城市里...',3,0),
(3,1,1,'第二章 别想睡觉！','众所周知，这里是一个十分友好的城市，在这个城市里...',1,2);
