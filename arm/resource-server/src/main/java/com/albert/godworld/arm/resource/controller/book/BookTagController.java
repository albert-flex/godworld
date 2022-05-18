package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookTag;
import com.albert.godworld.arm.resource.mapper.book.BookTagBindMapper;
import com.albert.godworld.arm.resource.mapper.book.BookTagMapper;
import com.albert.godworld.arm.resource.service.book.BookTagService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book_tag")
@AllArgsConstructor
public class BookTagController {

    private final BookTagService bookTagService;

    @GetMapping("/list")
    public List<BookTag> list(){
        return bookTagService.list();
    }
}
