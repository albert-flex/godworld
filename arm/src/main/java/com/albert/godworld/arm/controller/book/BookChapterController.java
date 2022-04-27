package com.albert.godworld.arm.controller.book;

import com.albert.godworld.arm.domain.book.BookChapter;
import com.albert.godworld.arm.service.book.BookChapterService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_chapter")
public class BookChapterController {

    private final BookChapterService bookChapterService;

    @Autowired
    public BookChapterController(BookChapterService bookChapterService) {
        this.bookChapterService = bookChapterService;
    }

    @GetMapping("/id/{id}")
    public BookChapter getById(@PathVariable("id") Long id){
        return this.bookChapterService.getById(id);
    }

    @GetMapping("/page/{volume_id}")
    public Page<BookChapter> pageOf(@PathVariable("volume_id") Long id,Page<BookChapter> page){
        return this.bookChapterService.pageOf(page,id);
    }

    @PostMapping
    public BookChapter addChapter(@RequestBody BookChapter bookChapter){
        return bookChapterService.publish(bookChapter);
    }

    @PutMapping
    public Boolean edit(@RequestBody BookChapter chapter){
        return bookChapterService.edit(chapter);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookChapterService.removeChapter(id);
    }
}
