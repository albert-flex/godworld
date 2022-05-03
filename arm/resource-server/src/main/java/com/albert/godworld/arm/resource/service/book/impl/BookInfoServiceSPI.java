package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.mapper.book.BookInfoMapper;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookInfoServiceSPI extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService {

    @Override
    public Page<BookInfo> pageOfAuthor(Page<BookInfo> page, String authorName) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookInfo::getAuthorName,authorName);
        return super.page(page,queryWrapper);
    }

    @Override
    public Page<BookInfo> pageOfBoard(Page<BookInfo> page, String boardName) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookInfo::getBoardName,boardName);
        return super.page(page,queryWrapper);
    }

    @Override
    public Page<BookInfo> pageOfUpdate(Page<BookInfo> page) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(BookInfo::getUpdateTime);
        return super.page(page,queryWrapper);
    }

    @Override
    public Page<BookInfo> queryByName(Page<BookInfo> page, String bookName) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(BookInfo::getName,bookName);
        return super.page(page,queryWrapper);
    }
}
