package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User>, UserDetailsService {

    Page<User> pageOf(Page<User> page, String name);

    boolean checkNameAvail(String name);

    User getByName(String name);
}

