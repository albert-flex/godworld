package com.albert.godworld.arm.service.user.impl;

import com.albert.godworld.arm.domain.user.UserGroup;
import com.albert.godworld.arm.mapper.user.UserGroupMapper;
import com.albert.godworld.arm.service.user.UserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceSPI extends ServiceImpl<UserGroupMapper, UserGroup>
    implements UserGroupService {

}

