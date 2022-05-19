package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookComment;
import com.albert.godworld.arm.resource.mapper.book.BookCommentMapper;
import com.albert.godworld.arm.resource.service.book.BookCommentService;
import com.albert.godworld.arm.resource.vo.BookCommentVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookCommentServiceSPI extends ServiceImpl<BookCommentMapper, BookComment>
implements BookCommentService {

    @Override
    public Page<BookCommentVo> pageOf(Page<BookCommentVo> page, Long bookId) {
        return super.baseMapper.pageOfBook(page,bookId);
    }

    @Override
    public Page<BookCommentVo> pageOfReader(Page<BookCommentVo> page, Long userId) {
        return super.baseMapper.pageOfUserId(page,userId);
    }
}
