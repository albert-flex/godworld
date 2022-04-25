package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookPart;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookPartService extends IService<BookPart> {

    //publish / edit / remove Book, Part, Volume, Chapter.
    BookPart publish(BookPart part);
    boolean edit(BookPart part);
    boolean removePart(Long id);

    Page<BookPart> pageOf(Page<BookPart> page, Long bookId);
}
