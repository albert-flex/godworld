package com.albert.godworld.arm.controller.book;

import com.albert.godworld.arm.domain.book.BookPart;
import com.albert.godworld.arm.service.book.BookPartService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_part")
public class BookPartController {

    private final BookPartService bookPartService;

    @Autowired
    public BookPartController(BookPartService bookPartService) {
        this.bookPartService = bookPartService;
    }

    @GetMapping("/id/{id}")
    public BookPart getById(@PathVariable("id") Long id){
        return bookPartService.getById(id);
    }

    @PostMapping
    public BookPart publish(@RequestBody BookPart bookPart){
        return bookPartService.publish(bookPart);
    }

    @PutMapping
    public Boolean edit(@RequestBody BookPart bookPart){
        return bookPartService.edit(bookPart);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookPartService.removePart(id);
    }

    @GetMapping("/page/{bookId}")
    public Page<BookPart> pageOf(@PathVariable("bookId") Long bookId, Page<BookPart> page){
        return bookPartService.pageOf(page,bookId);
    }
}
