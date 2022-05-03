package com.albert.godworld.arm.authorize.service;

import com.albert.godworld.arm.authorize.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User>, UserDetailsService {

    User getByName(String name);
}

