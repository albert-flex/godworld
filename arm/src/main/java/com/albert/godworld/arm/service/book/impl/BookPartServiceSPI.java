package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.domain.book.BookPart;
import com.albert.godworld.arm.mapper.book.BookPartMapper;
import com.albert.godworld.arm.service.author.AuthorRecordService;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.albert.godworld.arm.service.book.BookPartService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookPartServiceSPI extends ServiceImpl<BookPartMapper, BookPart>
        implements BookPartService {

    private final BookInfoService bookInfoService;
    private final AuthorRecordService authorRecordService;

    @Autowired
    public BookPartServiceSPI(BookInfoService bookInfoService, AuthorRecordService authorRecordService) {
        this.bookInfoService = bookInfoService;
        this.authorRecordService = authorRecordService;
    }

    @Override
    public BookPart publish(BookPart part) {
        BookInfo bookInfo = bookInfoService.getById(part.getBookId());
        if (bookInfo == null) return part;

        boolean result = super.save(part);
        if (!result) return part;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.PUBLISH_PART, part.getId());
        return part;
    }

    @Override
    public boolean edit(BookPart part) {
        BookInfo bookInfo = bookInfoService.getById(part.getBookId());
        if (bookInfo == null) return false;

        boolean result = super.updateById(part);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.EDIT_PART, part.getId());
        return true;
    }

    @Override
    public boolean removePart(Long id) {
        BookPart part = super.getById(id);
        BookInfo bookInfo = bookInfoService.getById(part.getBookId());
        if (bookInfo == null) return false;

        boolean result = super.removeById(id);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.REMOVE_PART, id);
        return true;
    }

    @Override
    public Page<BookPart> pageOf(Page<BookPart> page, Long bookId) {
        QueryWrapper<BookPart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_id",bookId);
        return super.page(page,queryWrapper);
    }
}
