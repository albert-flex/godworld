package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    Page<User> pageOf(Page<User> page, String name);

    User getByName(String name);
}

