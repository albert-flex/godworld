package com.albert.godworld.arm.resource.service.author;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface AuthorService extends IService<AuthorInfo> {

    Page<AuthorInfo> queryByName(Page<AuthorInfo> page,String name);
}