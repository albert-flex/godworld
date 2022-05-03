package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.service.book.BookShelfBookService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_shelf")
public class BookShelfController {

    private final BookShelfBookService bookShelfBookService;

    @Autowired
    public BookShelfController(BookShelfBookService bookShelfBookService) {
        this.bookShelfBookService = bookShelfBookService;
    }

    @GetMapping("/page/user/{userId}")
    public Page<BookInfo> pageOfUser(@PathVariable("userId") Long userId, Page<BookInfo> page){
        return bookShelfBookService.shelfOfUser(page,userId);
    }

    @GetMapping("/count/in_shelf/{bookId}")
    public Long countInShelf(@PathVariable("bookId") Long bookId){
        return bookShelfBookService.countInShelf(bookId);
    }

    @PostMapping("/in/{userId}/{bookId}")
    public Boolean in(@PathVariable("userId") Long userId,@PathVariable("bookId") Long bookId){
        return bookShelfBookService.addBook(userId,bookId);
    }

    @PostMapping("/out/{userId}/{bookId}")
    public Boolean out(@PathVariable("userId") Long userId,@PathVariable("bookId") Long bookId){
        return bookShelfBookService.removeBook(userId,bookId);
    }
}
