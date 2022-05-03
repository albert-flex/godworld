package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<Permission> {

    List<Permission> list(Long groupId);

    List<Permission> listOfUser(Long userId);

    boolean addToGroup(Long groupId, Long permissionId);

    boolean removeFromGroup(Long groupId, Long permissionId);
}

