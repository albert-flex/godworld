package com.albert.godworld.arm.resource.mapper.author;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.vo.author.AuthorNewestVo;
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
            "join book_info bi on bi.author_id=ai.id\n" +
            "where date_sub(curdate(), interval #{day} day) <= date(bi.create_time)\n" +
            "group by ai.id order by ai.id,bi.create_time desc")
    Page<AuthorNewestVo> pageNewestBookAuthors(Page<AuthorNewestVo> page, @Param("day") Integer day);

    @Select("")
    Page<AuthorNewestVo> pageUpdateBookAuthors(Page<AuthorNewestVo> page,@Param("day") Integer day);
}
