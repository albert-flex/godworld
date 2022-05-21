package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.social.SocialActivityBook;
import com.albert.godworld.arm.resource.vo.book.BookVo;
import com.albert.godworld.arm.resource.vo.book.SocialActivityBookVo;
import com.albert.godworld.arm.resource.vo.social.ActivityVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface SocialActivityBookService extends IService<SocialActivityBook> {

    Page<BookInfo> bookOf(Page<BookInfo> page, Long activityId);
    Page<BookVo> activityBookOfAuthor(Page<BookVo> page,Long activityId,Long authorId);
    Map<String, List<SocialActivityBookVo>> activityBooksOfAuthor(Long authorId);
    Map<String, ActivityVo> activityBookOfSocial(Long socialId);
    boolean addBook(Long bookId,Long activityId);
    boolean removeBook(Long bookId,Long activityId);
}
