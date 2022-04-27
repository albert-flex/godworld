package com.albert.godworld.arm.service.author;

import com.albert.godworld.arm.domain.author.AuthorInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthorService extends IService<AuthorInfo> {

    //绑定用户与作者信息，info的id应当为null
    boolean bind(Long userId,AuthorInfo info);

    boolean lock(Long authorId);

    boolean unLock(Long authorId);

    //接触用户与作者的绑定
    boolean unbind(Long authorId);

    Page<AuthorInfo> pageOf(Page<AuthorInfo> page,String name);

    //根据用户Id获取作者信息
    AuthorInfo findAuthorByUserId(Long userId);
}
