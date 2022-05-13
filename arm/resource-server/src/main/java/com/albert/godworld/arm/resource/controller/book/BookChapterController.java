package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.service.book.BookChapterService;
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

    @PostMapping
    public BookChapter create(@RequestBody BookChapter bookChapter){
        bookChapterService.save(bookChapter);
        return bookChapter;
    }

    @PutMapping
    public Boolean update(@RequestBody BookChapter bookChapter){
        return bookChapterService.updateById(bookChapter);
    }

    @GetMapping("/page/cata/{cataId}")
    public Page<BookChapter> chapterOfCata(@PathVariable("cataId")Long cataId,Page<BookChapter> page){
        return bookChapterService.chapterOfVolume(page,cataId);
    }

    @GetMapping("/newest/{bookId}")
    public BookChapter newestChapter(@PathVariable("bookId") Long bookId){
        return bookChapterService.newestChapter(bookId);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookChapterService.removeById(id);
    }
}
