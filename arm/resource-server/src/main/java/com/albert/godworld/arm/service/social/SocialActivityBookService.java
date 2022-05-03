package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.domain.social.SocialActivityBook;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialActivityBookService extends IService<SocialActivityBook> {

    Page<BookInfo> bookOf(Page<BookInfo> page,Long activityId);
    boolean addBook(Long bookId,Long activityId);
    boolean removeBook(Long bookId,Long activityId);
}
