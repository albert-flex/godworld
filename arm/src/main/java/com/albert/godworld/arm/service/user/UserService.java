package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    User loadFromUserName(String userName);

    Page<User> pageOf(Page<User> page,String userName);

    boolean checkPassword(String userName,String passWord);
}
