package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.albert.godworld.arm.domain.book.BookChapter;
import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.mapper.book.BookChapterMapper;
import com.albert.godworld.arm.service.author.AuthorRecordService;
import com.albert.godworld.arm.service.book.BookChapterService;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookChapterServiceSPI extends ServiceImpl<BookChapterMapper, BookChapter>
    implements BookChapterService {

    private final BookInfoService bookInfoService;
    private final AuthorRecordService authorRecordService;

    public BookChapterServiceSPI(BookInfoService bookInfoService, AuthorRecordService authorRecordService) {
        this.bookInfoService = bookInfoService;
        this.authorRecordService = authorRecordService;
    }

    @Override
    public BookChapter publish(BookChapter chapter) {
        BookInfo bookInfo = bookInfoService.getById(chapter.getBookId());
        if (bookInfo == null) return chapter;

        boolean result = super.save(chapter);
        if (!result) return chapter;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.PUBLISH_CHAPTER, chapter.getId());
        return chapter;
    }

    @Override
    public boolean edit(BookChapter chapter) {
        BookInfo bookInfo = bookInfoService.getById(chapter.getBookId());
        if (bookInfo == null) return false;

        boolean result = super.updateById(chapter);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.EDIT_CHAPTER, chapter.getId());
        return true;
    }

    @Override
    public boolean removeChapter(Long id) {
        BookChapter bookChapter = super.getById(id);
        BookInfo bookInfo = bookInfoService.getById(bookChapter.getBookId());
        if (bookInfo == null) return false;

        boolean result = super.removeById(id);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.REMOVE_CHAPTER, id);
        return true;
    }

    @Override
    public Page<BookChapter> pageOf(Page<BookChapter> page, Long volumeId) {
        QueryWrapper<BookChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("volume_id",volumeId);
        return super.page(page,queryWrapper);
    }
}
