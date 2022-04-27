package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

public interface PermissionService extends IService<Permission> {

    Permission getOne(@PathVariable("name") String name);
}
