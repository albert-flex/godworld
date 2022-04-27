package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.Permission;
import com.albert.godworld.arm.mapper.user.PermissionMapper;
import com.albert.godworld.arm.service.user.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceSPI extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {

    @Override
    public Permission getOne(String name) {
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);

        return super.getOne(queryWrapper);
    }
}
