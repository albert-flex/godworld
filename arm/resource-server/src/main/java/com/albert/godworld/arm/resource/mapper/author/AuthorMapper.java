package com.albert.godworld.arm.resource.mapper.author;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.vo.author.AuthorNewestVo;
import com.albert.godworld.arm.resource.vo.author.AuthorUpdatedVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthorMapper extends BaseMapper<AuthorInfo> {

    @Select("select id from author_info " +
            "where user_id=#{userId}")
    Long getAuthorIdByUserId(Long userId);

    @Select("select ai.id,ai.name,bi.name as book_name,bi.create_time\n" +
            "from author_info ai\n" +
            "left join book_info bi on bi.author_id=ai.id\n" +
            "where date_sub(curdate(), interval #{day} day) <= date(bi.create_time)\n" +
            "group by ai.id order by ai.id,bi.create_time desc")
    Page<AuthorNewestVo> pageNewestBookAuthors(Page<AuthorNewestVo> page, @Param("day") Integer day);

    @Select("select ai.id,ai.name as author_name,si.name as social_name,bi.name as book_name,bi.update_time,bc.title as chapter,bv.name volume_name,ai.moto\n" +
            "from author_info ai\n" +
            "left join social_member sm on sm.author_id=ai.id\n" +
            "left join social_info si on si.id=sm.social_id\n" +
            "join book_info bi on bi.author_id=ai.id\n" +
            "join book_chapter bc on bc.id=bi.update_chapter_id\n" +
            "join book_volume bv on bv.id=bc.volume_id\n" +
            "where date_sub(curdate(), interval #{day} day) <= date(bi.create_time)\n" +
            "group by ai.id order by bc.update_time desc")
    Page<AuthorUpdatedVo> pageUpdateBookAuthors(Page<AuthorUpdatedVo> page,@Param("day") Integer day);

    @Select("select ai.id,ai.name as author_name,si.name as social_name,bi.name as book_name,bi.update_time,bc.title as chapter,bv.name volume_name,ai.moto\n" +
            "from author_info ai\n" +
            "left join social_member sm on sm.author_id=ai.id\n" +
            "left join social_info si on si.id=sm.social_id\n" +
            "left join book_info bi on bi.author_id=ai.id\n" +
            "left join book_chapter bc on bc.id=bi.update_chapter_id\n" +
            "left join book_volume bv on bv.id=bc.volume_id\n" +
            "where date_sub(curdate(), interval '7' day) <= date(bi.create_time)\n" +
            "and ai.name like concat('%',#{name},'%')\n" +
            "group by ai.id order by bc.update_time desc")
    Page<AuthorUpdatedVo> queryByName(Page<AuthorUpdatedVo> page,@Param("name") String name);

    @Select("select ai.id,ai.name as author_name,si.name as social_name,bi.name as book_name,bi.update_time,bc.title as chapter,bv.name volume_name,ai.moto\n" +
            "from author_info ai\n" +
            "left join social_member sm on sm.author_id=ai.id\n" +
            "left join social_info si on si.id=sm.social_id\n" +
            "left join book_info bi on bi.author_id=ai.id\n" +
            "left join book_chapter bc on bc.id=bi.update_chapter_id\n" +
            "left join book_volume bv on bv.id=bc.volume_id\n" +
            "where date_sub(curdate(), interval '7' day) <= date(bi.create_time)\n" +
            "and si.name like concat('%',#{social},'%')\n" +
            "group by ai.id order by bc.update_time desc")
    Page<AuthorUpdatedVo> queryBySocial(Page<AuthorUpdatedVo> page,@Param("social") String social);
}
