package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.GroupPermission;
import com.albert.godworld.arm.domain.user.Permission;
import com.albert.godworld.arm.mapper.user.PermissionMapper;
import com.albert.godworld.arm.service.user.GroupPermissionService;
import com.albert.godworld.arm.service.user.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PermissionServiceSPI extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {

    private final GroupPermissionService groupPermissionService;

    @Autowired
    public PermissionServiceSPI(GroupPermissionService groupPermissionService) {
        this.groupPermissionService = groupPermissionService;
    }

    @Override
    public List<Permission> list(Long groupId) {
        LambdaQueryWrapper<GroupPermission> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(GroupPermission::getGroupId,groupId);
        List<GroupPermission> groupPermissions=groupPermissionService.list(queryWrapper);

        List<Long> ids=new LinkedList<>();
        for(int i=0;i!=groupPermissions.size();++i){
            ids.add(groupPermissions.get(i).getPermissionId());
        }

        return super.listByIds(ids);
    }

    @Override
    public List<Permission> listOfUser(Long userId) {
        return super.baseMapper.allPerOfUser(userId);
    }

    @Override
    public boolean addToGroup(Long groupId, Long permissionId) {
        removeFromGroup(groupId,permissionId);

        GroupPermission permission=new GroupPermission();
        permission.setPermissionId(permissionId);
        permission.setGroupId(groupId);
        return groupPermissionService.save(permission);
    }

    @Override
    public boolean removeFromGroup(Long groupId, Long permissionId) {
        LambdaQueryWrapper<GroupPermission> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(GroupPermission::getGroupId,groupId);
        queryWrapper.eq(GroupPermission::getPermissionId,permissionId);

        return groupPermissionService.remove(queryWrapper);
    }
}

