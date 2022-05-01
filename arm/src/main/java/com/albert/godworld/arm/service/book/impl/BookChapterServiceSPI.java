package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookChapter;
import com.albert.godworld.arm.mapper.book.BookChapterMapper;
import com.albert.godworld.arm.service.book.BookChapterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookChapterServiceSPI extends ServiceImpl<BookChapterMapper, BookChapter>
    implements BookChapterService {

    @Override
    public Page<BookChapter> chapterOfCata(Page<BookChapter> page, Long cataId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getCataId,cataId);
        return super.page(page,queryWrapper);
    }

    @Override
    public BookChapter newestChapter(Long bookId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getBookId,bookId);
        queryWrapper.orderByDesc(BookChapter::getCreateTime);
        return super.getOne(queryWrapper);
    }
}
