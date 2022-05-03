package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.book.BookShelfBook;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookShelfBookService extends IService<BookShelfBook> {

    boolean addBook(Long userId,Long bookId);
    boolean removeBook(Long userId,Long bookId);
    long countInShelf(Long bookId);
    Page<BookInfo> shelfOfUser(Page<BookInfo> page, Long userId);
}
