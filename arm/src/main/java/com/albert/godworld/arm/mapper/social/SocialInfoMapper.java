package com.albert.godworld.arm.mapper.social;

import com.albert.godworld.arm.domain.social.SocialInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SocialInfoMapper extends BaseMapper<SocialInfo> {

    @Select("""
            select id,name,master_id,moto
            from social_info si
            join social_member sm on sm.social_id=si.id
            join author_info ai on ai.id=sm.author_id
            where sm.author_id=#{authorId}
            """)
    SocialInfo byAuthorId(Long authorId);
}
