package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.GroupPermission;
import com.albert.godworld.arm.domain.user.Permission;
import com.albert.godworld.arm.mapper.user.GroupPermissionMapper;
import com.albert.godworld.arm.service.user.GroupPermissionService;
import com.albert.godworld.arm.service.user.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GroupPermissionServiceSPI extends ServiceImpl<GroupPermissionMapper, GroupPermission>
    implements GroupPermissionService {

    private final PermissionService permissionService;

    @Autowired
    public GroupPermissionServiceSPI(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public List<Permission> listOf(Long groupId) {
        QueryWrapper<GroupPermission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("group_id",groupId);
        List<GroupPermission> list=super.list(queryWrapper);
        List<Long> ids=new LinkedList<>();
        for(int i=0;i!=list.size();++i){
            ids.add(list.get(i).getPermissionId());
        }

        return permissionService.listByIds(ids);
    }

    @Override
    public boolean addPermission(Long groupId, Long permissionId) {
        QueryWrapper<GroupPermission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("group_id",groupId);
        queryWrapper.eq("permission_id",permissionId);
        GroupPermission permission=super.getOne(queryWrapper);
        if(permission!=null)return false;

        GroupPermission permission1=new GroupPermission();
        permission1.setPermissionId(permissionId);
        permission1.setGroupId(groupId);

        return super.save(permission1);
    }

    @Override
    public boolean removePermission(Long groupId, Long permissionId) {
        QueryWrapper<GroupPermission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("group_id",groupId);
        queryWrapper.eq("permission_id",permissionId);

        return super.remove(queryWrapper);
    }
}
