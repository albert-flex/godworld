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

    @PostMapping
    public BookInfo create(@RequestBody BookInfo bookInfo){
        bookInfoService.save(bookInfo);
        return bookInfo;
    }

    @PutMapping
    public Boolean update(@RequestBody BookInfo bookInfo){
        return bookInfoService.updateById(bookInfo);
    }

    @GetMapping("/page/newest")
    public Page<BookInfo> pageOfNewes(Page<BookInfo> page){
        return bookInfoService.pageOfUpdate(page);
    }

    @GetMapping("/page/author/{authorName}")
    public Page<BookInfo> pageOfAuthor(@PathVariable("authorName") String authorName,Page<BookInfo> page){
        return bookInfoService.pageOfAuthor(page,authorName);
    }

    @GetMapping("/page/board/{boardName}")
    public Page<BookInfo> pageOfBoard(@PathVariable("boardName") String boardName,Page<BookInfo> page){
        return bookInfoService.pageOfBoard(page,boardName);
    }

    @GetMapping("/page/all")
    public Page<BookInfo> all(Page<BookInfo> page){
        return bookInfoService.page(page);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookInfoService.removeById(id);
    }
}
