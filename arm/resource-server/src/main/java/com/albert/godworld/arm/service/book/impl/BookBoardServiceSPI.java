package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookBoard;
import com.albert.godworld.arm.mapper.book.BookBoardMapper;
import com.albert.godworld.arm.service.book.BookBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookBoardServiceSPI extends ServiceImpl<BookBoardMapper, BookBoard>
implements BookBoardService {

}
