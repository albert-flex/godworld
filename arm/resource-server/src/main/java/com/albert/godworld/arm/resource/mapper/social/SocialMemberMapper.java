package com.albert.godworld.arm.resource.mapper.social;

import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.vo.social.SocialMemberSimpleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SocialMemberMapper extends BaseMapper<SocialMember> {

    @Select("select ai.id,sm.social_id,sm.member_name,sm.type,ai.name as author_name,\n" +
            "ai.present_book_id,bi.name as present_book_name\n" +
            "from social_member sm\n" +
            "left join author_info ai on ai.id=sm.author_id\n" +
            "left join book_info bi on bi.id=ai.present_book_id\n" +
            "where sm.social_id=#{socialId} and sm.type=#{type}\n")
    Page<SocialMemberSimpleVo> memberOfType(Page<SocialMemberSimpleVo> page,
                                            @Param("socialId") Long socialId, @Param("type") SocialMemberType type);
}
