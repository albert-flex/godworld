package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookTag;
import com.albert.godworld.arm.resource.domain.book.BookTagBind;
import com.albert.godworld.arm.resource.mapper.book.BookTagBindMapper;
import com.albert.godworld.arm.resource.mapper.book.BookTagMapper;
import com.albert.godworld.arm.resource.service.book.BookTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookTagServiceSPI extends ServiceImpl<BookTagMapper,BookTag> implements BookTagService {

    private final BookTagMapper bookTagMapper;
    private final BookTagBindMapper bookTagBindMapper;

    @Override
    @Transactional
    public void changeBookTag(Long bookId, BookTag[] tags) {
        LambdaQueryWrapper<BookTagBind> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookTagBind::getBookId,bookId);
        bookTagBindMapper.delete(queryWrapper);

        for (BookTag tag : tags) {
            BookTagBind bind = new BookTagBind();
            bind.setBookId(bookId);
            bind.setTagId(tag.getId());
            bookTagBindMapper.insert(bind);
        }
    }
}
