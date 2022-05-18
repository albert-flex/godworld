package com.albert.godworld.arm.resource.mapper.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.vo.BookVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    @Select("<script>\n" +
            "select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
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
    Page<BookVo> query(Page<BookVo> page,@Param("tags") List<String> tags,@Param("board") String board,
                       @Param("year") Integer year,@Param("month") Integer month);

    @Select("select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "group by bi.id\n" +
            "order by bi.point desc\n" +
            "limit 0,10;")
    List<BookVo> queryOfPoint();

    @Select("select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where bb.name=#{board}\n" +
            "group by bi.id\n" +
            "order by bi.point desc\n" +
            "limit 0,10;")
    List<BookVo> queryOfPointAtBoard(@Param("board") String board);

    @Select("select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where ai.name=#{name}\n" +
            "group by bi.id\n" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByAuthorName(Page<BookVo> page, @Param("name") String name);

    @Select("select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where bb.name=#{board}\n" +
            "group by bi.id\n" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByBoard(Page<BookVo> page,@Param("board") String board);
    @Select("<script>" +
            "select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where bt.name in \n" +
            "<foreach collection='tags' item='item' open='(' separator=',' close=')'>\n" +
            "#{item}\n" +
            "</foreach>" +
            "group by bi.id\n" +
            "order by bi.create_time desc\n" +
            "</script>")
    Page<BookVo> queryByTags(Page<BookVo> page, @Param("tags") List<String> tags);

    @Select("select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where bi.name like concat('%',#{name},'%')\n" +
            "group by bi.id\n" +
            "order by bi.create_time desc ")
    Page<BookVo> queryByName(Page<BookVo> page, @Param("name") String name);

    @Select("select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "where bi.id=#{id}\n" +
            "group by bi.id\n")
    BookVo getById(Long id);

    @Select("select bi.id,bi.update_time,bb.name as boardName,bi.name,bi.word_count,\n" +
            "group_concat(bt.name separator ',') as tag_words,\n" +
            "bi.description,ai.name as author,bi.create_time\n" +
            "from book_info bi\n" +
            "join book_board bb on bb.id=bi.board_id\n" +
            "join author_info ai on ai.id=bi.author_id\n" +
            "join book_tag_bind btb on btb.book_id=bi.id\n" +
            "join book_tag bt on bt.id=btb.tag_id\n" +
            "group by bi.id\n" +
            "order by bi.update_time desc")
    Page<BookVo> getUpdatePage(Page<BookVo> page);
}
