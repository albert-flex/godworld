package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.Group;
import com.albert.godworld.arm.mapper.user.GroupMapper;
import com.albert.godworld.arm.service.user.GroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceSPI extends ServiceImpl<GroupMapper, Group>
    implements GroupService {

    @Override
    public Group getOne(String name) {
        QueryWrapper<Group> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);

        return super.getOne(queryWrapper);
    }

    @Override
    public Page<Group> pageOf(Page<Group> page, String name) {
        QueryWrapper<Group> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);

        return super.page(page,queryWrapper);
    }
}
