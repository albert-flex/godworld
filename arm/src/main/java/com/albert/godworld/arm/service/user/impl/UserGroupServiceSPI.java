package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.Group;
import com.albert.godworld.arm.domain.user.UserGroup;
import com.albert.godworld.arm.mapper.user.UserGroupMapper;
import com.albert.godworld.arm.service.user.GroupService;
import com.albert.godworld.arm.service.user.UserGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserGroupServiceSPI extends ServiceImpl<UserGroupMapper, UserGroup>
        implements UserGroupService {

    private final GroupService groupService;

    @Autowired
    public UserGroupServiceSPI(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public List<Group> listGroupOf(Long userId) {
        QueryWrapper<UserGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserGroup> groups = super.list(queryWrapper);

        List<Long> ids=new LinkedList<>();
        for(int i=0;i!=groups.size();++i){
            ids.add(groups.get(i).getGroupId());
        }

        return groupService.listByIds(ids);
    }

    @Override
    public boolean removeGroup(Long userId, Long groupId) {
        QueryWrapper<UserGroup> updateWrapper=new QueryWrapper<>();
        updateWrapper.eq("user_id",userId);
        updateWrapper.eq("group_id",groupId);

        return super.remove(updateWrapper);
    }

    @Override
    public boolean addGroup(Long userId, Long groupId) {
        QueryWrapper<UserGroup> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("group_id",groupId);
        UserGroup group=super.getOne(queryWrapper);
        if(group!=null)return false;

        UserGroup group1=new UserGroup();
        group1.setGroupId(groupId);
        group1.setUserId(userId);

        return super.save(group1);
    }
}
