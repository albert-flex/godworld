package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookChapter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookChapterService extends IService<BookChapter> {

    Page<BookChapter> chapterOfCata(Page<BookChapter> page,Long cataId);
    long countChapter(Long bookId);
    BookChapter newestChapter(Long bookId);
}
