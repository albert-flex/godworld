package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.Group;
import com.albert.godworld.arm.domain.user.UserGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserGroupService extends IService<UserGroup> {

    List<Group> listGroupOf(Long userId);
    boolean removeGroup(Long userId,Long groupId);
    boolean addGroup(Long userId,Long groupId);
}
