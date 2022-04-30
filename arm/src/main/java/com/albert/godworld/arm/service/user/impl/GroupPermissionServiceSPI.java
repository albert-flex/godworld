package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.GroupPermission;
import com.albert.godworld.arm.mapper.user.GroupPermissionMapper;
import com.albert.godworld.arm.service.user.GroupPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GroupPermissionServiceSPI extends ServiceImpl<GroupPermissionMapper, GroupPermission>
    implements GroupPermissionService {
}

