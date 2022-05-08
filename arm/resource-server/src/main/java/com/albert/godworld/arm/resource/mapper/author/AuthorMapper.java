package com.albert.godworld.arm.resource.mapper.author;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthorMapper extends BaseMapper<AuthorInfo> {

    @Select("select id from author_info " +
            "where user_id=#{userId}")
    Long getAuthorIdByUserId(Long userId);
}
