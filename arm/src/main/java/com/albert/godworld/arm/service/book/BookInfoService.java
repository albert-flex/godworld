package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookInfoService extends IService<BookInfo> {

    //publish un_publish / create / edit / remove Book
    boolean publish(Long bookId);
    BookInfo create(BookInfo bookInfo);

    Page<BookInfo> pageOf(Page<BookInfo> page,Long authorId);

    boolean edit(BookInfo bookInfo);
    boolean removeBook(Long id);
    boolean unPublish(Long bookId);
}
