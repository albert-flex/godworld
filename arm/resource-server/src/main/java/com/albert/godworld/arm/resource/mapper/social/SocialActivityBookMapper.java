package com.albert.godworld.arm.resource.mapper.social;

import com.albert.godworld.arm.resource.domain.social.SocialActivityBook;
import com.albert.godworld.arm.resource.vo.book.BookVo;
import com.albert.godworld.arm.resource.vo.book.SocialActivityBookVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SocialActivityBookMapper extends BaseMapper<SocialActivityBook> {

    @Select("select sa.id as activity_id,sa.name as activity_name,bi.id as book_id,bi.name as book_name,ai.id as author_id,\n" +
            "ai.name as author_name,\n" +
            "group_concat(bt.name separator ',') as tag_words,bi.update_time\n" +
            "from book_info bi\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join social_activity_book sab on sab.book_id=bi.id\n" +
            "join social_activity sa on sa.id=sab.social_id\n" +
            "left join book_tag_bind btb on btb.book_id=bi.id\n" +
            "left join book_tag bt on bt.id=btb.tag_id\n" +
            "where ai.id=#{authorId}\n" +
            "group by bi.id order by sa.id")
    List<SocialActivityBookVo> activityBookOfAuthor(@Param("authorId") Long authorId);
}
