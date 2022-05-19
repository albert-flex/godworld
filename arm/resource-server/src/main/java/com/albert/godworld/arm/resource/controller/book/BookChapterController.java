package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.service.book.BookChapterService;
import com.albert.godworld.arm.resource.vo.book.BookChapterInfoVo;
import com.albert.godworld.arm.resource.vo.book.BookVolumeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/page/volume/{volumeId}")
    public Page<BookChapter> chapterOfVolume(@PathVariable("volumeId")Long volumeId,Page<BookChapter> page){
        return bookChapterService.chapterOfVolume(page,volumeId);
    }

    @GetMapping("/volumes/{bookId}")
    public List<BookVolumeVo> volumeVos(@PathVariable("bookId") Long bookId){
        return bookChapterService.volumeOfBook(bookId);
    }

    @GetMapping("/id/{id}")
    public BookChapterInfoVo getInfoByChapter(@PathVariable("id") Long id){
        return bookChapterService.getChapter(id);
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
