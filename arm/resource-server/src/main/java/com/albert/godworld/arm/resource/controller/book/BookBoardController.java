package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookBoard;
import com.albert.godworld.arm.resource.service.book.BookBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book_board")
public class BookBoardController {

    private final BookBoardService bookBoardService;

    @Autowired
    public BookBoardController(BookBoardService bookBoardService) {
        this.bookBoardService = bookBoardService;
    }

    @GetMapping("/id/{id}")
    public BookBoard getById(@PathVariable("id") Long id){
        return bookBoardService.getById(id);
    }

    @PostMapping
    public BookBoard create(@RequestBody BookBoard bookBoard){
        bookBoardService.save(bookBoard);
        return bookBoard;
    }

    @GetMapping("/list")
    public List<BookBoard> listAll(){
        return bookBoardService.list();
    }

}
