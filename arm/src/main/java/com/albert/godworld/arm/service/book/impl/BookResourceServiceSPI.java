package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.domain.book.BookResource;
import com.albert.godworld.arm.mapper.book.BookResourceMapper;
import com.albert.godworld.arm.service.author.AuthorRecordService;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.albert.godworld.arm.service.book.BookResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookResourceServiceSPI extends ServiceImpl<BookResourceMapper,BookResource>
    implements BookResourceService {

    private final BookInfoService bookInfoService;
    private final AuthorRecordService authorRecordService;

    @Autowired
    public BookResourceServiceSPI(BookInfoService bookInfoService, AuthorRecordService authorRecordService) {
        this.bookInfoService = bookInfoService;
        this.authorRecordService = authorRecordService;
    }


    @Override
    public BookResource publish(BookResource resource) {
        BookInfo bookInfo = bookInfoService.getById(resource.getBookId());
        if (bookInfo == null) return resource;

        boolean result = super.save(resource);
        if (!result) return resource;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.PUBLISH_RESOURCE, resource.getId());
        return resource;
    }

    @Override
    public boolean removeResource(Long resourceId) {

        BookResource resource = super.getById(resourceId);
        if (resource == null) return false;

        BookInfo bookInfo = bookInfoService.getById(resource.getBookId());
        if (bookInfo == null) return false;

        boolean result = super.removeById(resourceId);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.UN_PUBLISH_RESOURCE, resourceId);
        return true;
    }

    @Override
    public Page<BookResource> getBookResource(Page<BookResource> page, Long chapterId) {
        QueryWrapper<BookResource> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        return super.page(page,queryWrapper);
    }
}
