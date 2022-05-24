package com.albert.godworld.arm.resource.mapper.user;

import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.UserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select ur.id as user_id,ur.username as user_name,\n" +
            "ai.id as author_id,ai.email,sm.id as social_id,sm.id as member_id\n" +
            "from user ur\n" +
            "left join author_info ai on ai.user_id=ur.id\n" +
            "left join social_member sm on sm.author_id=ai.id\n" +
            "where ur.id=#{userId}")
    UserDTO loadById(Long userId);
}

