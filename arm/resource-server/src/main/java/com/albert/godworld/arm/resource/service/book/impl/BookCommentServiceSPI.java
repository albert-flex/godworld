package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookComment;
import com.albert.godworld.arm.resource.mapper.book.BookCommentMapper;
import com.albert.godworld.arm.resource.service.book.BookCommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookCommentServiceSPI extends ServiceImpl<BookCommentMapper, BookComment>
implements BookCommentService {

    @Override
    public Page<BookComment> pageOf(Page<BookComment> page, Long bookId) {
        LambdaQueryWrapper<BookComment> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookComment::getBookId,bookId);
        queryWrapper.orderByDesc(BookComment::getCreateTime);
        return super.page(page,queryWrapper);
    }

    @Override
    public Page<BookComment> pageOfReader(Page<BookComment> page, Long userId) {
        LambdaQueryWrapper<BookComment> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookComment::getUserId,userId);
        queryWrapper.orderByDesc(BookComment::getCreateTime);
        return super.page(page,queryWrapper);
    }
}
