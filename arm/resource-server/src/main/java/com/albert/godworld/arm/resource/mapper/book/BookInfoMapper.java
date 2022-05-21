package com.albert.godworld.arm.resource.mapper.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.dto.BookDTO;
import com.albert.godworld.arm.resource.vo.book.BookVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {


    @Insert("insert into book_info(name,author_id,description,board_id) values\n" +
            "(#{book.name},#{book.authorId},#{book.description},\n" +
            "(select b.id from book_board b where b.name=#{book.boardName}))")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int create(@Param("book") BookDTO bookDTO);

    String bookVoPre = "select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,bc.title as update_chapter,\n" +
            "(case when(bi.id=ai.present_book_id) then 1 else 0 end) as is_present,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "left join book_board bb on bb.id=bi.board_id\n" +
            "left join author_info ai on ai.id=bi.author_id\n" +
            "left join book_tag_bind btb on btb.book_id=bi.id\n" +
            "left join book_tag bt on bt.id=btb.tag_id\n" +
            "left join book_chapter bc on bc.id=bi.update_chapter_id\n";

    @Select("<script>\n" +
            bookVoPre +
            "where\n" +
            "bb.name=#{board}\n" +
            "and bt.name in " +
            "<foreach collection='tags' item='item' open='(' separator=',' close=')'>\n" +
            "#{item}\n" +
            "</foreach>" +
            "and year(bi.create_time)=#{year}\n" +
            "and month(bi.create_time)=#{month}\n" +
            "group by bi.id\n" +
            "order by bi.update_time desc\n" +
            "</script>")
    Page<BookVo> query(Page<BookVo> page, @Param("tags") List<String> tags, @Param("board") String board,
                       @Param("year") Integer year, @Param("month") Integer month);

    @Select(bookVoPre +
            "group by bi.id\n" +
            "order by bi.point desc\n" +
            "limit 0,10;")
    List<BookVo> queryOfPoint();

    @Select(bookVoPre +
            "where bb.name=#{board}\n" +
            "group by bi.id\n" +
            "order by bi.point desc\n" +
            "limit 0,10;")
    List<BookVo> queryOfPointAtBoard(@Param("board") String board);

    @Select(bookVoPre +
            "where ai.name=#{name}\n" +
            "group by bi.id\n" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByAuthorName(Page<BookVo> page, @Param("name") String name);

    @Select(bookVoPre +
            "where ai.id=#{authorId}\n" +
            "group by bi.id\n" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByAuthorId(Page<BookVo> page, @Param("authorId") Long authorId);

    @Select(bookVoPre +
            "where bb.name=#{board}\n" +
            "group by bi.id\n" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByBoard(Page<BookVo> page, @Param("board") String board);

    @Select("<script>" +
            bookVoPre +
            "where bt.name in \n" +
            "<foreach collection='tags' item='item' open='(' separator=',' close=')'>\n" +
            "#{item}\n" +
            "</foreach>" +
            "group by bi.id\n" +
            "order by bi.create_time desc\n" +
            "</script>")
    Page<BookVo> queryByTags(Page<BookVo> page, @Param("tags") List<String> tags);

    @Select(bookVoPre +
            "where bi.name like concat('%',#{name},'%')\n" +
            "group by bi.id\n" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByName(Page<BookVo> page, @Param("name") String name);

    @Select(bookVoPre +
            "where bi.id=#{id}\n" +
            "group by bi.id\n")
    BookVo getById(Long id);

    @Select(bookVoPre +
            "group by bi.id\n" +
            "order by bi.update_time desc")
    Page<BookVo> getUpdatePage(Page<BookVo> page);
}
