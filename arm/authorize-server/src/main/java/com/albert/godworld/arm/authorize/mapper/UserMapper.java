package com.albert.godworld.arm.authorize.mapper;

import com.albert.godworld.arm.authorize.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

