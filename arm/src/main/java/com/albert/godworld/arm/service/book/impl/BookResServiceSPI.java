package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookRes;
import com.albert.godworld.arm.mapper.book.BookResMapper;
import com.albert.godworld.arm.service.book.BookResService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookResServiceSPI extends ServiceImpl<BookResMapper, BookRes>
    implements BookResService {
}
