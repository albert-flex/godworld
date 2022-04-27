package com.albert.godworld.arm.controller.book;

import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookInfoController {

    private final BookInfoService bookInfoService;

    @Autowired
    public BookInfoController(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @GetMapping("/id/{id}")
    public BookInfo getById(@PathVariable("id") Long id){
        return bookInfoService.getById(id);
    }

    @GetMapping("/page/{authorId}")
    public Page<BookInfo> pageOf(@PathVariable("authorId") Long authorId, Page<BookInfo> page){
        return bookInfoService.pageOf(page,authorId);
    }

    @PostMapping
    public BookInfo create(@RequestBody BookInfo bookInfo){
        return bookInfoService.create(bookInfo);
    }

    @PutMapping
    public Boolean edit(@RequestBody BookInfo bookInfo){
        return bookInfoService.edit(bookInfo);
    }

    @PutMapping("/publish/{id}")
    public Boolean publish(@PathVariable("id") Long id){
        return bookInfoService.publish(id);
    }

    @PutMapping("/un_publish/{id}")
    public Boolean un_publish(@PathVariable("id") Long id){
        return bookInfoService.unPublish(id);
    }

    @DeleteMapping("/{id}")
    public Boolean removeById(@PathVariable("id") Long id){
        return bookInfoService.removeBook(id);
    }
}
