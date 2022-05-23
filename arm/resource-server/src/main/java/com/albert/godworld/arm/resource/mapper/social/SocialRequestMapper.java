package com.albert.godworld.arm.resource.mapper.social;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.vo.social.SocialRequestVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SocialRequestMapper extends BaseMapper<SocialRequest> {

    @Select("select sr.id,sr.author_id,sr.social_id,sr.message,sr.content,\n" +
            "sr.create_time,sr.type,ai.name as author_name,sr.handled,\n" +
            "sr.handle_member_id,sr.confirm\n" +
            "from social_request sr\n" +
            "left join author_info ai on ai.id=sr.author_id\n" +
            "where sr.social_id=#{socialId}")
    Page<SocialRequestVo> getBySocialId(Page<SocialRequestVo> page, @Param("socialId") Long socialId);
}
