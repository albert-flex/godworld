package com.albert.godworld.arm.mapper.author;

import com.albert.godworld.arm.domain.author.AuthorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper extends BaseMapper<AuthorInfo> {
}
