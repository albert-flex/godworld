package com.albert.godworld.godarm.author.service;

import com.albert.godworld.godarm.author.domain.AuthorInfo;
import com.albert.godworld.godarm.author.domain.AuthorRecord;

public interface AuthorServices {

    //注册一个作者
    AuthorInfo registerAuthor(Long userId,AuthorInfo info);

    //更新作者信息
    Boolean updateAuthor(AuthorInfo info);

    //禁止一个作者（包括发布、修改作品）
    Boolean disableAuthor(Long authorId);

    //取消禁止作者
    Boolean enableAuthor(Long authorId);

    //注销一个作者
    Boolean removeAuthor(Long authorId);

    //记录作者的一个操作
    Boolean record(AuthorRecord record);
}
