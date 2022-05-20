package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.service.social.SocialActivityBookService;
import com.albert.godworld.arm.resource.vo.book.SocialActivityBookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
                                   Page<BookInfo> page) {
        return socialActivityBookService.bookOf(page, activityId);
    }

    @GetMapping("/author/{authorId}")
    public Map<String, List<SocialActivityBookVo>> activityBooksOfAuthor(
            @PathVariable("authorId") Long authorId) {
        return socialActivityBookService.activityBooksOfAuthor(authorId);
    }

    @PostMapping("/add/{bookId}/{activityId}")
    public Boolean addBook(@PathVariable("bookId") Long bookId, @PathVariable("activityId") Long activityId) {
        return socialActivityBookService.addBook(bookId, activityId);
    }

    @PostMapping("/remove/{bookId}/{activityId}")
    public Boolean removeBook(@PathVariable("bookId") Long bookId, @PathVariable("activityId") Long activityId) {
        return socialActivityBookService.removeBook(bookId, activityId);
    }
}
