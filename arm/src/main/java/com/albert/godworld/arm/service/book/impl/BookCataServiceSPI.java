package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookCata;
import com.albert.godworld.arm.mapper.book.BookCataMapper;
import com.albert.godworld.arm.service.book.BookCataService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookCataServiceSPI extends ServiceImpl<BookCataMapper,BookCata>
    implements BookCataService {

    @Override
    public Page<BookCata> rootCataOfBook(Page<BookCata> page, Long bookId) {
        LambdaQueryWrapper<BookCata> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookCata::getBookId,bookId);
        queryWrapper.eq(BookCata::getParentId,0L);
        return super.page(page,queryWrapper);
    }

    @Override
    public Page<BookCata> cataOfParent(Page<BookCata> page, Long parentId) {
        LambdaQueryWrapper<BookCata> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookCata::getParentId,parentId);
        return super.page(page,queryWrapper);
    }
}
