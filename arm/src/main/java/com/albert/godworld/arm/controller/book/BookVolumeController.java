package com.albert.godworld.arm.controller.book;

import com.albert.godworld.arm.domain.book.BookVolume;
import com.albert.godworld.arm.service.book.BookVolumeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_volume")
public class BookVolumeController {

    private final BookVolumeService bookVolumeService;

    @Autowired
    public BookVolumeController(BookVolumeService bookVolumeService) {
        this.bookVolumeService = bookVolumeService;
    }

    @GetMapping("/id/{id}")
    public BookVolume getById(@PathVariable("id") Long id){
        return bookVolumeService.getById(id);
    }

    @GetMapping("/page/{part_id}")
    public Page<BookVolume> pageOf(@PathVariable("part_id") Long partId,Page<BookVolume> page){
        return bookVolumeService.pageOf(page,partId);
    }

    @PutMapping
    public Boolean edit(@RequestBody BookVolume volume){
        return bookVolumeService.edit(volume);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookVolumeService.removeVolume(id);
    }
}
