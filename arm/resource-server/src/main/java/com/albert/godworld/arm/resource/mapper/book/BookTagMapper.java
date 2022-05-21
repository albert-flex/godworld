package com.albert.godworld.arm.resource.mapper.book;

import com.albert.godworld.arm.resource.domain.book.BookTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookTagMapper extends BaseMapper<BookTag> {

    //添加Tag绑定时使用
    @Select("<script>\n" +
            "select id\n" +
            "from book_tag bt\n" +
            "where bt.name in\n" +
            "<foreach collection='names' item='item' open='(' separator=',' close=')'>\n" +
            "#{item}\n" +
            "</foreach>\n" +
            "</script>")
    List<Long> tagIdListOf(@Param("names") List<String> names);

    //需要删除情况Tag绑定时使用
    @Select("<script>" +
            "select btb.id from book_tag_bind btb\n" +
            "left join book_tag bt on bt.id=btb.tag_id\n" +
            "where btb.book_id=#{bookId} and \n" +
            "bt.name in\n" +
            "<foreach collection='names' item='item' open='(' separator=',' close=')'>\n" +
            "#{item}\n" +
            "</foreach>\n" +
            "</script>")
    List<Long> idOfBookOnTagBinds(@Param("names")List<String> names,
                              @Param("bookId") Long bookId);
}
