package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.albert.godworld.arm.domain.book.BookComment;
import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.mapper.book.BookCommentMapper;
import com.albert.godworld.arm.service.author.AuthorRecordService;
import com.albert.godworld.arm.service.book.BookCommentService;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCommentServiceSPI extends ServiceImpl<BookCommentMapper,BookComment>
    implements BookCommentService {

    private final BookInfoService bookInfoService;
    private final AuthorRecordService authorRecordService;

    @Autowired
    public BookCommentServiceSPI(BookInfoService bookInfoService, AuthorRecordService authorRecordService) {
        this.bookInfoService = bookInfoService;
        this.authorRecordService = authorRecordService;
    }

    @Override
    public boolean like(Long commentId) {
        BookComment comment = super.getById(commentId);
        if (comment == null) return false;

        BookInfo bookInfo = bookInfoService.getById(comment.getBookId());
        if (bookInfo == null) return false;

        if (comment.getFavor()) return true;

        comment.setFavor(true);
        boolean result = super.updateById(comment);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.LIKE_COMMENT, commentId);
        return true;
    }

    @Override
    public boolean unlike(Long commentId) {
        BookComment comment = super.getById(commentId);
        if (comment == null) return false;

        BookInfo bookInfo = bookInfoService.getById(comment.getBookId());
        if (bookInfo == null) return false;

        if (!comment.getFavor()) return true;

        comment.setFavor(false);
        boolean result = super.updateById(comment);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.UN_LIKE_COMMENT, commentId);
        return true;
    }

    @Override
    public Page<BookComment> likeOf(Page<BookComment> page, Long bookId) {
        QueryWrapper<BookComment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_id",bookId);
        queryWrapper.eq("favor",true);
        return super.page(page,queryWrapper);
    }
}
