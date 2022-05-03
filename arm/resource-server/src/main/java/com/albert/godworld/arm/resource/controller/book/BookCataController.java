package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookCata;
import com.albert.godworld.arm.resource.service.book.BookCataService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_cata")
public class BookCataController {

    private final BookCataService bookCataService;

    @Autowired
    public BookCataController(BookCataService bookCataService) {
        this.bookCataService = bookCataService;
    }

    @GetMapping("/page/book/{bookId}")
    public Page<BookCata> rootOfBook(@PathVariable("bookId") Long bookId, Page<BookCata> page){
        return bookCataService.rootCataOfBook(page,bookId);
    }

    @GetMapping("/page/cata/{cataId}")
    public Page<BookCata> cataOf(@PathVariable("cataId") Long cataId,Page<BookCata> page){
        return bookCataService.cataOfParent(page,cataId);
    }

    @PostMapping
    public BookCata create(@RequestBody BookCata bookCata){
        bookCataService.save(bookCata);
        return bookCata;
    }

    @PutMapping
    public Boolean update(@RequestBody BookCata cata){
        return bookCataService.updateById(cata);
    }

    @GetMapping("/id/{id}")
    public BookCata bookCataById(@PathVariable("id") Long id){
        return bookCataService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookCataService.removeById(id);
    }
}
