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
(1,"ADMIN","管理员"),
(2,"READER","读者"),
(3,"AUTHOR","作者"),
(4,"SOCIAL","社员"),
(5,"SOCIAL_ADMIN","社团管理员");

delete from user;
insert into user(id,username,email) values
(1,"A1","natsufumij@yeah.net"),
(2,"A2","A2@godworld.com"),
(3,"A3","A3@godworld.com"),
(4,"A4","A4@godworld.com"),
(5,"A5","A5@godworld.com"),
(6,"A6","A6@godworld.com");

delete from group_permission;
insert into group_permission(id,group_id,permission_id) values
(1,1,1),
(2,2,2),
(3,3,3),
(4,4,4),
(5,5,5);

delete from user_group;
insert into user_group(id,user_id,group_id) values
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,2,2),
(7,3,2),
(8,3,3),
(9,3,4),
(10,4,1),
(11,4,2),
(12,4,3),
(13,4,4),
(14,4,5);

/**
 * Author
 */
delete from author_info;
insert author_info(id,name,user_id,email,moto,present_book_id) values
(1,"夏文纯一",1,"natsufumij@yeah.net","恭喜你，尤克，从今后你就是A级冒险者了",1),
(2,"阿尔伯特",3,"albert_flex@yeah.net","恭喜你，尤克，从今后你就是A级冒险者了",7),
(3,'夏文悠二',4,"youji@yeah.net","夏文悠二的问题箱，请多指教.",3),
(4,'夏文狂三',5,"kurumi@yeah.net","夏文狂三的帝帝刻，请多指教.",4),
(5,'夏文四郎',5,"sirou@yeah.net","夏文四郎的指纹，请多指教.",0);

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
insert book_info(id,author_id,name,description,board_id,update_chapter_id) values
(1,1,"樱花变奏曲","樱花飞舞的初春，这是我们相遇的季节。",3,2),
(2,3,"谋杀者Z","我想要谋杀一个人，但是无论如何也成功不了。",3,5),
(3,3,"被封印的魔力","每一个人都有一股被封印的魔力，他会让人发出强大的力量。",4,8),
(4,4,"无界法魂","我会为你报仇，师傅",9,0),
(5,3,"康德莱特街39号","见证人心与人心的隔阂与联系。",3,0),
(6,2,"Spring EE的开发方式","笨重又无法舍弃的开发方式",7,0),
(7,2,"Java EE的正确开发方式","古老又传统的方法",7,0);

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
(23,6,13),
(24,7,13);

delete from book_volume;
insert into book_volume(id,book_id,name) values
(1,1,'第一卷 嗯，这个名字不错'),
(2,2,'第一卷 起点'),
(3,3,'第一卷 空间与时间');

delete from book_chapter;
insert into book_chapter(id,book_id,volume_id,title,content,prev_chapter_id,next_chapter_id)values
(1,1,1,'第一章 新生报告会','众所周知，这里是一个十分友好的城市，在这个城市里...\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',0,3),
(2,1,1,'第三章 可爱的人是谁','众所周知，这里是一个十分友好的城市，在这个城市里...\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',3,0),
(3,1,1,'第二章 别想睡觉！','众所周知，这里是一个十分友好的城市，在这个城市里...、\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',1,2),
(4,2,2,'第一章 爱丽丝','众所周知，这里是一个十分友好的城市，在这个城市里...\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',0,6),
(5,2,2,'第三章 莉莉丝','众所周知，这里是一个十分友好的城市，在这个城市里...\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',6,0),
(6,2,2,'第二章 菲利斯','众所周知，这里是一个十分友好的城市，在这个城市里...、\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',4,5),
(7,3,3,'第一章 异变开始','众所周知，这里是一个十分友好的城市，在这个城市里...\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',0,9),
(8,3,3,'第三章 最狂的人','众所周知，这里是一个十分友好的城市，在这个城市里...\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',9,0),
(9,3,3,'第二章 异能觉醒','众所周知，这里是一个十分友好的城市，在这个城市里...、\n众所周知，这里是一个十分友好的城市，在这个城市里...\n',7,8);

delete from social_info;
insert into social_info(id,name,master_id) values
(1,'玄沧阁',1);

delete from social_member;
insert into social_member(id,social_id,author_id,member_name,type) values
(1,1,1,'AA','MASTER'),
(2,1,2,'BB','NORMAL'),
(3,1,3,'CC','ADMIN'),
(4,1,4,'DD','NORMAL'),
(5,1,5,'EE','ADMIN');

delete from social_activity;
insert into social_activity(id,social_id,admin_member_id,name,description,start_time,end_time) values
(1,1,1,"2020文集","逆转季节---",'2020-06-09 21:21:22','2020-09-09 21:21:22'),
(2,1,1,"2022文集","泛白天空---",'2022-06-09 21:21:22','2022-09-09 21:21:22');

delete from social_activity_book;
insert into social_activity_book(id,social_id,activity_id,book_id) values
(1,1,1,1),
(2,1,1,2),
(3,1,1,3),
(5,1,2,1),
(6,1,2,2);

delete from social_announce;
insert into social_announce(id,social_id,admin_member_id,title,content) values
(1,1,1,"关于2022文集...","关于2022年的文集，有消息说..."),
(2,1,1,"关于2020文集...","关于2020年的文集，有消息说...");

delete from social_request;
insert into social_request(id,social_id,author_id,type,message,content) values
(1,1,2,1,"[夏文纯一]想 加入社团 [玄沧阁]",""),
(2,1,3,2,"[夏文悠二]想 退出社团 [玄沧阁]",""),
(3,1,4,4,"[夏文纯一]想 退出活动 [2022文集],[《火之异能者》]","activityId:11;bookId:291"),
(4,1,3,6,"[夏文纯一]想 退出管理员 [玄沧阁]",""),
(5,1,6,3,"[夏文纯一]想 参加活动 [2022文集],[《无界法魂》]","activityId:101;bookId:101");
