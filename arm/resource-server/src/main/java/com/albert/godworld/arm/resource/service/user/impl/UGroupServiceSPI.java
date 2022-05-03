package com.albert.godworld.arm.resource.service.user.impl;

import com.albert.godworld.arm.resource.domain.user.UGroup;
import com.albert.godworld.arm.resource.domain.user.UserGroup;
import com.albert.godworld.arm.resource.mapper.user.UGroupMapper;
import com.albert.godworld.arm.resource.service.user.UGroupService;
import com.albert.godworld.arm.resource.service.user.UserGroupService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UGroupServiceSPI extends ServiceImpl<UGroupMapper, UGroup>
        implements UGroupService {

    private final UserGroupService userGroupService;

    @Autowired
    public UGroupServiceSPI(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Override
    public List<UGroup> list(Long userId) {
        LambdaQueryWrapper<UserGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGroup::getUserId, userId);
        List<UserGroup> groups = userGroupService.list(queryWrapper);

        List<Long> ids = new LinkedList<>();
        for (int i = 0; i != groups.size(); ++i) {
            ids.add(groups.get(i).getGroupId());
        }

        return super.listByIds(ids);
    }

    @Override
    public boolean addToUser(Long userId, Long groupId) {
        removeFromUser(userId,groupId);

        UserGroup userGroup=new UserGroup();
        userGroup.setGroupId(groupId);
        userGroup.setUserId(userId);
        return userGroupService.save(userGroup);
    }

    @Override
    public boolean removeFromUser(Long userId, Long groupId) {
        LambdaQueryWrapper<UserGroup> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGroup::getGroupId,groupId);
        queryWrapper.eq(UserGroup::getUserId,userId);
        return userGroupService.remove(queryWrapper);
    }
}
