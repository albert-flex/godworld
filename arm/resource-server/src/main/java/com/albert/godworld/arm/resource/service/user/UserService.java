package com.albert.godworld.arm.resource.service.user;

import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.UserDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User>, UserDetailsService {

    Page<User> pageOf(Page<User> page, String name);

    UserDTO loadById(Long userId);

    boolean checkNameAvail(String name);

    boolean checkEmailAvail(String email);

    User getByName(String name);

    boolean insert(User user);
}

