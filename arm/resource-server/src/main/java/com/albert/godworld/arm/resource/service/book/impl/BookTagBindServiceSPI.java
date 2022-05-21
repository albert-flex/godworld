package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookTagBind;
import com.albert.godworld.arm.resource.mapper.book.BookTagBindMapper;
import com.albert.godworld.arm.resource.service.book.BookTagBindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookTagBindServiceSPI extends ServiceImpl<BookTagBindMapper, BookTagBind>
    implements BookTagBindService {
}
