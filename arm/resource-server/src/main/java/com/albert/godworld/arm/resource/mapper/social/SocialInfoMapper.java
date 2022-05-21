package com.albert.godworld.arm.resource.mapper.social;

import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.vo.social.SocialInfoVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewActVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewAnnVo;
import com.albert.godworld.arm.resource.vo.social.SocialReVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("select l.* from(select si.id,si.name,sa.description as act_desc,sa.name as act_name\n" +
            "from social_info si\n" +
            "join social_activity sa on sa.social_id=si.id\n" +
            "order by sa.start_time desc limit 1000) l group by l.id\n")
    Page<SocialNewActVo> newActPage(Page<SocialNewActVo> page);

    @Select("select l.* from(select si.id,si.name,sa.content as announce_content,sa.title as announce_name\n" +
            "from social_info si\n" +
            "join social_announce sa on sa.social_id=si.id\n" +
            "order by sa.publish_time limit 10000) l group by l.id")
    Page<SocialNewAnnVo> newAnnPage(Page<SocialNewAnnVo> page);

    @Select("select si.id,si.name,si.moto,ai.id as master_id,ai.name as master_name\n" +
            "from social_info si\n" +
            "left join author_info ai on ai.id=si.master_id\n" +
            "where si.name like concat('%',#{name},'%')")
    Page<SocialReVo> query(Page<SocialReVo> page,@Param("name") String name);

    @Select("select l.* from(select si.id,si.name,si.moto,ai.id as master_id,sa.start_time,ai.name as master_name,si.create_time,sa.name newest_act,saa.title newest_announce\n" +
            "from social_info si\n" +
            "left join social_activity sa on sa.social_id=si.id\n" +
            "left join social_announce saa on saa.social_id=si.id\n" +
            "left join author_info ai on ai.id=si.master_id\n" +
            "where si.id=#{id}\n" +
            "order by sa.start_time desc limit 10000) \n" +
            "l group by l.id")
    SocialInfoVo getById(@Param("id") Long id);
}
