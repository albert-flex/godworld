package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookInfoService extends IService<BookInfo> {

    Page<BookInfo> pageOfAuthor(Page<BookInfo> page,String authorName);

    Page<BookInfo> pageOfBoard(Page<BookInfo> page,String boardName);
    Page<BookInfo> pageOfUpdate(Page<BookInfo> page);
    Page<BookInfo> queryByName(Page<BookInfo> page,String bookName);
}
