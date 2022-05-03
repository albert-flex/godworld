package com.albert.godworld.arm.controller.social;

import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.domain.social.SocialActivity;
import com.albert.godworld.arm.domain.social.SocialActivityBook;
import com.albert.godworld.arm.domain.social.SocialInfo;
import com.albert.godworld.arm.service.social.SocialActivityBookService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social_activity_book")
public class SocialActivityBookController {

    private final SocialActivityBookService socialActivityBookService;

    @Autowired
    public SocialActivityBookController(SocialActivityBookService socialActivityBookService) {
        this.socialActivityBookService = socialActivityBookService;
    }

    @GetMapping("/of/{activityId}")
    public Page<BookInfo> bookPage(@PathVariable("activityId") Long activityId,
                                             Page<BookInfo> page){
        return socialActivityBookService.bookOf(page,activityId);
    }

    @PostMapping("/add/{bookId}/{activityId}")
    public Boolean addBook(@PathVariable("bookId") Long bookId,@PathVariable("activityId") Long activityId){
        return socialActivityBookService.addBook(bookId,activityId);
    }

    @PostMapping("/remove/{bookId}/{activityId}")
    public Boolean removeBook(@PathVariable("bookId") Long bookId,@PathVariable("activityId") Long activityId){
        return socialActivityBookService.removeBook(bookId,activityId);
    }
}
