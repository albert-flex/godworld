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

    String bookPre = "select sa.id as activity_id,sa.name as activity_name,bi.id as book_id, bi.name as book_name,\n" +
            "ai.id as author_id,ai.name as author_name, bi.description as description,sa.act_on,\n" +
            "sa.description as activity_desc, group_concat(bt.name separator ',') as tag_words,bi.update_time \n" +
            "from social_activity sa \n" +
            "left join social_activity_book sab on sab.activity_id=sa.id \n" +
            "left join social_info si on si.id=sab.social_id \n" +
            "left join book_info bi on bi.id=sab.book_id \n" +
            "left join author_info ai on ai.id=bi.author_id \n" +
            "left join book_tag_bind btb on btb.book_id=bi.id \n" +
            "left join book_tag bt on bt.id=btb.tag_id ";

    @Select(bookPre +
            "where ai.id=#{authorId}\n" +
            "group by sab.id order by sa.id,\n" +
            "sa.start_time desc")
    List<SocialActivityBookVo> activityBookOfAuthor(@Param("authorId") Long authorId);

    @Select(bookPre +
            "where sa.social_id=#{socialId}\n" +
            "group by sab.id order by\n" +
            "sa.start_time desc")
    List<SocialActivityBookVo> activityBookOfSocial(@Param("socialId") Long socialId);

    @Select(bookPre +
            "where sa.id=#{activityId}\n" +
            "and ai.id=#{authorId}\n" +
            "group by sab.id order by\n" +
            "sa.start_time desc")
    Page<SocialActivityBookVo> onActivityBookOfAuthor(@Param("activityId") Long activityId,@Param("authorId") Long authorId);
}
