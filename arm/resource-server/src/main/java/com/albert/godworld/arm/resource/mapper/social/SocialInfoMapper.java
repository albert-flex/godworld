package com.albert.godworld.arm.resource.mapper.social;

import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SocialInfoMapper extends BaseMapper<SocialInfo> {

    @Select("select si.id as id,si.name as name," +
            "si.master_id as master_id,si.moto as moto\n" +
            "from social_info si\n" +
            "join social_member sm on sm.social_id=si.id\n" +
            "join author_info ai on ai.id=sm.author_id\n" +
            "where sm.author_id=#{authorId}\n")
    SocialInfo byAuthorId(Long authorId);
}
