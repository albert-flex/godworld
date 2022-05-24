package com.albert.godworld.arm.resource.service.user.impl;

import com.albert.godworld.arm.resource.domain.user.Permission;
import com.albert.godworld.arm.resource.domain.user.UGroups;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.UserDTO;
import com.albert.godworld.arm.resource.mapper.user.UserMapper;
import com.albert.godworld.arm.resource.service.user.PermissionService;
import com.albert.godworld.arm.resource.service.user.UGroupService;
import com.albert.godworld.arm.resource.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceSPI extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final PermissionService permissionService;
    private final UGroupService uGroupService;

    @Override
    public Page<User> pageOf(Page<User> page, String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUsername, name);
        return super.page(page, queryWrapper);
    }

    @Override
    public UserDTO loadById(Long userId) {
        return super.baseMapper.loadById(userId);
    }

    @Override
    public boolean checkNameAvail(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, name).last("limit 1");
        return super.getOne(queryWrapper) == null;
    }

    @Override
    public boolean checkEmailAvail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email).last("limit 1");
        return super.getOne(queryWrapper) == null;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, name).last("limit 1");
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean insert(User user) {
        if(!save(user))return false;

        return uGroupService.addToUser(user.getId(), UGroups.READER.getCode());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<Permission> permissions = permissionService.listOfUser(user.getId());
        user.setAllPermission(permissions);
        return user;
    }
}

