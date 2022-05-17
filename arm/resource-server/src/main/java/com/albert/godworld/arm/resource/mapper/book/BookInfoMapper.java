package com.albert.godworld.arm.resource.mapper.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.dto.BookQueryDTO;
import com.albert.godworld.arm.resource.vo.BookVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    @Select("select bi.id,bb.name as boardName,bi.name,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where\n" +
            "bb.name=#{board}\n" +
            "and bt.name in #{tags}\n" +
            "and year(bi.create_time)=#{year}\n" +
            "and month(bi.create_time)=#{month}\n" +
            "order by bi.update_time desc\n" +
            "group by bi.id;")
    Page<BookVo> query(Page<BookVo> page,@Param("tags") List<Long> tags,@Param("board") String board,
                       @Param("year") Integer year,@Param("month") Integer month);

    @Select("select bi.id,bb.name as boardName,bi.name,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "order by bi.count desc\n" +
            "group by bi.id" +
            "limit 10")
    List<BookVo> queryOfPoint();

    @Select("select bi.id,bb.name as boardName,bi.name,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where bi.id=#{boardId}" +
            "order by bi.count desc\n" +
            "group by bi.id" +
            "limit 10")
    List<BookVo> queryOfPointAtBoard(@Param("boardId") Long boardId);

    @Select("select bi.id,bb.name as boardName,bi.name,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where ai.name=#{name}" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByAuthorName(Page<BookVo> page, @Param("name") String name);
}
