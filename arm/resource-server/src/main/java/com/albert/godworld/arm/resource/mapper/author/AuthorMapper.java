package com.albert.godworld.arm.resource.mapper.author;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper extends BaseMapper<AuthorInfo> {
}
