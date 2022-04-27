package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GroupService extends IService<Group> {

    Group getOne(String name);

    Page<Group> pageOf(Page<Group> page,String name);
}
