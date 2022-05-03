package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookRes;
import com.albert.godworld.arm.resource.mapper.book.BookResMapper;
import com.albert.godworld.arm.resource.service.book.BookResService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookResServiceSPI extends ServiceImpl<BookResMapper, BookRes>
    implements BookResService {
}
