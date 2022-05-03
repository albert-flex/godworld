package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookCata;
import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.mapper.book.BookCataMapper;
import com.albert.godworld.arm.resource.service.book.BookCataService;
import com.albert.godworld.arm.resource.service.book.BookChapterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCataServiceSPI extends ServiceImpl<BookCataMapper, BookCata>
    implements BookCataService {

    private final BookChapterService bookChapterService;

    @Autowired
    public BookCataServiceSPI(BookChapterService bookChapterService) {
        this.bookChapterService = bookChapterService;
    }

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

    @Override
    public boolean removeCata(Long cataId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getCataId,cataId);
        if(bookChapterService.getOne(queryWrapper)!=null){
            return false;
        }

        return super.removeById(cataId);
    }
}
