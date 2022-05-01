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

    @GetMapping("/id/{id{")
    public BookComment bookComment(@PathVariable("id") Long id){
        return bookCommentService.getById(id);
    }

    @GetMapping("/page/book/{bookId}")
    public Page<BookComment> pageOfBook(@PathVariable("bookId") Long bookId, Page<BookComment> page){
        return bookCommentService.pageOf(page,bookId);
    }

    @GetMapping("/page/reader/{readerName}")
    public Page<BookComment> pageOfReader(@PathVariable("readerName") String readerName,Page<BookComment> page){
        return bookCommentService.pageOfReader(page,readerName);
    }

    @PostMapping
    public BookComment create(@RequestBody BookComment bookComment){
        bookCommentService.save(bookComment);
        return bookComment;
    }

    @PutMapping
    public Boolean update(@RequestBody BookComment bookComment){
        return bookCommentService.updateById(bookComment);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id")Long id){
        return bookCommentService.removeById(id);
    }
}
