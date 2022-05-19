package com.albert.godworld.arm.resource.mapper.book;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.vo.BookChapterInfoVo;
import com.albert.godworld.arm.resource.vo.BookChapterVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookChapterMapper extends BaseMapper<BookChapter> {

    @Select("select bc.*,bv.name as volumeTitle\n" +
            "from book_chapter bc\n" +
            "join book_volume bv on bv.id=bc.volume_id\n" +
            "where bc.book_id=#{bookId}\n" +
            "order by bc.prev_chapter_id")
    List<BookChapterVo> listChaptersOfBook(Long bookId);

    @Select("select bc.*,bv.name as volumeTitle,bi.name as bookName\n" +
            "from book_chapter bc\n" +
            "join book_info bi on bi.id=bc.book_id\n" +
            "join book_volume bv on bv.id=bc.volume_id\n" +
            "where bc.id=#{chapterId}")
    BookChapterInfoVo getInfoOnChapter(Long chapterId);
}
