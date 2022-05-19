package com.albert.godworld.arm.resource.mapper.book;

import com.albert.godworld.arm.resource.domain.book.BookComment;
import com.albert.godworld.arm.resource.vo.book.BookCommentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookCommentMapper extends BaseMapper<BookComment> {

    @Select("select bc.*,ur.username as userName,bi.name as bookName\n" +
            "from book_comment bc\n" +
            "join user ur on ur.id=bc.user_id\n" +
            "join book_info bi on bi.id=bc.book_id\n" +
            "where bc.book_id=#{bookId}\n" +
            "group by bc.id\n" +
            "order by bc.create_time desc")
    Page<BookCommentVo> pageOfBook(Page<BookCommentVo> page, @Param("bookId") Long bookId);

    @Select("select bc.*,ur.username as userName,bi.name as bookName\n" +
            "from book_comment bc\n" +
            "join user ur on ur.id=bc.user_id\n" +
            "join book_info bi on bi.id=bc.book_id\n" +
            "where bc.user_id=#{userId}\n" +
            "group by bc.id\n" +
            "order by bc.create_time desc")
    Page<BookCommentVo> pageOfUserId(Page<BookCommentVo> page, @Param("userId") Long userId);

}
