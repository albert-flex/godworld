package com.albert.godworld.arm.service.author;

import com.albert.godworld.arm.domain.author.AuthorInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface AuthorService extends IService<AuthorInfo> {

    Page<AuthorInfo> queryByName(Page<AuthorInfo> page,String name);
}
