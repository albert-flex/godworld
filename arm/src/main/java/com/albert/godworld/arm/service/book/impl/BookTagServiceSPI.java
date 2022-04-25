package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookTag;
import com.albert.godworld.arm.mapper.book.BookTagMapper;
import com.albert.godworld.arm.service.book.BookTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookTagServiceSPI extends ServiceImpl<BookTagMapper, BookTag>
    implements BookTagService {
}
