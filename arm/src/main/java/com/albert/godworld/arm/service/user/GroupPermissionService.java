package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.GroupPermission;
import com.albert.godworld.arm.domain.user.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GroupPermissionService extends IService<GroupPermission> {
    List<Permission> listOf(Long groupId);
    boolean addPermission(Long groupId, Long permissionId);
    boolean removePermission(Long groupId, Long permissionId);
}
