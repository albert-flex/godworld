package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookVolume;
import com.albert.godworld.arm.resource.service.book.BookVolumeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_volume")
@AllArgsConstructor
public class BookVolumeController {

    private final BookVolumeService bookVolumeService;

    @PostMapping("/create")
    public Boolean create(@RequestParam("bookId") Long bookId, @RequestParam("name") String name, @RequestParam
            ("prevVolumeId") Long prevVolumeId){
        return bookVolumeService.create(bookId,name,prevVolumeId);
    }
}
