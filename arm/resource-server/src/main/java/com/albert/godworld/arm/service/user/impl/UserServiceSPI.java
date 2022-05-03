package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.Permission;
import com.albert.godworld.arm.domain.user.User;
import com.albert.godworld.arm.mapper.user.UserMapper;
import com.albert.godworld.arm.service.user.PermissionService;
import com.albert.godworld.arm.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceSPI extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final PermissionService permissionService;

    @Autowired
    public UserServiceSPI(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public Page<User> pageOf(Page<User> page, String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUserName, name);
        return super.page(page, queryWrapper);
    }

    @Override
    public boolean checkNameAvail(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, name);
        return super.getOne(queryWrapper) == null;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, name);
        return super.getOne(queryWrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getByName(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }

        List<Permission> permissions=permissionService.listOfUser(user.getId());
        List<String> strings=new LinkedList<>();
        for(int i=0;i!=permissions.size();++i){
            strings.add(permissions.get(i).getName());
        }
        String[] as=new String[strings.size()];
        strings.toArray(as);

        return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                .password(user.getPassword())
                .authorities(as).build();
    }
}

