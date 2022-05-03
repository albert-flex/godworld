package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookBoard;
import com.albert.godworld.arm.resource.mapper.book.BookBoardMapper;
import com.albert.godworld.arm.resource.service.book.BookBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookBoardServiceSPI extends ServiceImpl<BookBoardMapper, BookBoard>
implements BookBoardService {

}
