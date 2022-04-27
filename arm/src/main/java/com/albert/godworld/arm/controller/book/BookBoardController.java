package com.albert.godworld.arm.controller.book;

import com.albert.godworld.arm.controller.CurdController;
import com.albert.godworld.arm.domain.book.BookBoard;
import com.albert.godworld.arm.service.book.BookBoardService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/book_board")
public class BookBoardController extends CurdController<BookBoard> {

    private final BookBoardService bookBoardService;

    @Autowired
    public BookBoardController(BookBoardService bookBoardService) {
        this.bookBoardService = bookBoardService;
    }

    @Override
    protected IService<BookBoard> service() {
        return bookBoardService;
    }
}
