package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.vo.BookChapterInfoVo;
import com.albert.godworld.arm.resource.vo.BookVolumeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookChapterService extends IService<BookChapter> {

    Page<BookChapter> chapterOfVolume(Page<BookChapter> page, Long volumeId);
    long countChapter(Long bookId);
    BookChapter newestChapter(Long bookId);

    List<BookVolumeVo> volumeOfBook(Long bookId);

    BookChapterInfoVo getChapter(Long chapterId);
}
