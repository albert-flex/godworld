package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.social.SocialActivityBook;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialActivityBookService extends IService<SocialActivityBook> {

    Page<BookInfo> bookOf(Page<BookInfo> page, Long activityId);
    boolean addBook(Long bookId,Long activityId);
    boolean removeBook(Long bookId,Long activityId);
}
