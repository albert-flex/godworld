package com.albert.godworld.arm.controller.book;

import com.albert.godworld.arm.domain.book.BookRes;
import com.albert.godworld.arm.service.book.BookResService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_res")
public class BookResController {

    private final BookResService bookResService;

    @Autowired
    public BookResController(BookResService bookResService) {
        this.bookResService = bookResService;
    }

    @PostMapping
    public BookRes create(@RequestBody BookRes res){
        bookResService.save(res);
        return res;
    }

    @PutMapping
    public Boolean update(@RequestBody BookRes bookInfo){
        return bookResService.updateById(bookInfo);
    }

    @GetMapping("/page")
    public Page<BookRes> page(Page<BookRes> page){
        return bookResService.page(page);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookResService.removeById(id);
    }

}
