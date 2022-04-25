package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.mapper.book.BookInfoMapper;
import com.albert.godworld.arm.service.author.AuthorRecordService;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookInfoServiceSPI extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService {

    private final AuthorRecordService authorRecordService;

    @Autowired
    public BookInfoServiceSPI(AuthorRecordService authorRecordService) {
        this.authorRecordService = authorRecordService;
    }

    @Override
    public boolean publish(Long bookId) {
        BookInfo bookInfo = super.getById(bookId);
        bookInfo.setUpShelf(true);

        boolean result = super.updateById(bookInfo);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.PUBLISH_BOOK, bookId);
        return true;
    }

    @Override
    public BookInfo create(BookInfo bookInfo) {
        boolean result = super.save(bookInfo);
        if (!result) return bookInfo;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.PUBLISH_BOOK, bookInfo.getId());
        return bookInfo;
    }

    @Override
    public boolean edit(BookInfo bookInfo) {
        boolean result = super.updateById(bookInfo);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.EDIT_BOOK, bookInfo.getId());
        return true;
    }

    @Override
    public boolean removeBook(Long id) {
        BookInfo bookInfo = super.getById(id);
        if (bookInfo == null) return false;

        boolean result = super.removeById(id);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.REMOVE_BOOK, bookInfo.getId());
        return true;
    }

    @Override
    public boolean unPublish(Long bookId) {
        BookInfo bookInfo = super.getById(bookId);
        bookInfo.setUpShelf(false);

        boolean result = super.updateById(bookInfo);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.UN_PUBLISH_BOOK, bookId);
        return true;
    }
}
