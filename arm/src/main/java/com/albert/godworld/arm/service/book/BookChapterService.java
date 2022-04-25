package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookChapter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookChapterService extends IService<BookChapter> {

    //publish / edit / remove Book, Part, Volume, Chapter.
    BookChapter publish(BookChapter chapter);
    boolean edit(BookChapter chapter);
    boolean removeChapter(Long id);

    Page<BookChapter> pageOf(Page<BookChapter> page, Long volumeId);
}
