package com.albert.godworld.arm.resource.mapper.social;

import com.albert.godworld.arm.resource.domain.social.SocialAnnounce;
import com.albert.godworld.arm.resource.vo.social.AnnounceVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SocialAnnounceMapper extends BaseMapper<SocialAnnounce> {

    @Select("select sa.id,sa.title,ai.id as author_id,ai.name as author_name,sa.content,sa.publish_time\n" +
            "from social_announce sa\n" +
            "join social_info si on si.id=sa.social_id\n" +
            "left join social_member sm on sa.admin_member_id=sm.id and sa.social_id=si.id\n" +
            "left join author_info ai on ai.id=sm.author_id\n" +
            "where si.id=#{socialId}\n" +
            "order by sa.publish_time desc")
    Page<AnnounceVo> pageOfSocial(Page<AnnounceVo> page, @Param("socialId") Long socialId);
}
