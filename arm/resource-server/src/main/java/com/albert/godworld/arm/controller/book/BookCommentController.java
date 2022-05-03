package com.albert.godworld.arm.controller.book;

import com.albert.godworld.arm.domain.book.BookComment;
import com.albert.godworld.arm.service.book.BookCommentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_comment")
public class BookCommentController {

    private final BookCommentService bookCommentService;

    @Autowired
    public BookCommentController(BookCommentService bookCommentService) {
        this.bookCommentService = bookCommentService;
    }

    @GetMapping("/page/book/{bookId}")
    public Page<BookComment> pageOfBook(@PathVariable("bookId") Long bookId, Page<BookComment> page){
        return bookCommentService.pageOf(page,bookId);
    }

    @GetMapping("/page/user/{userId}")
    public Page<BookComment> pageOfReader(@PathVariable("userId") Long userId,Page<BookComment> page){
        return bookCommentService.pageOfReader(page,userId);
    }

    @PostMapping
    public BookComment create(@RequestBody BookComment bookComment){
        bookCommentService.save(bookComment);
        return bookComment;
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id")Long id){
        return bookCommentService.removeById(id);
    }
}
