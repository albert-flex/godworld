package com.albert.godworld.arm.resource.service.user.impl;

import com.albert.godworld.arm.resource.domain.user.UserGroup;
import com.albert.godworld.arm.resource.mapper.user.UserGroupMapper;
import com.albert.godworld.arm.resource.service.user.UserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceSPI extends ServiceImpl<UserGroupMapper, UserGroup>
    implements UserGroupService {

}

