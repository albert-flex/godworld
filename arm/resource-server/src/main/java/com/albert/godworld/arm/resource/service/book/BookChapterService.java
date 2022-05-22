package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.dto.ChapterDTO;
import com.albert.godworld.arm.resource.vo.book.BookChapterInfoVo;
import com.albert.godworld.arm.resource.vo.book.BookVolumeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookChapterService extends IService<BookChapter> {

    Page<BookChapter> chapterOfVolume(Page<BookChapter> page, Long volumeId);
    long countChapter(Long bookId);
    BookChapter newestChapter(Long bookId);
    boolean insertChapter(ChapterDTO dto);

    List<BookVolumeVo> volumeOfBook(Long bookId);

    BookChapterInfoVo getChapter(Long chapterId);
}
