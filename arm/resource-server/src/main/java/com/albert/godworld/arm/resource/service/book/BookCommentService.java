package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookComment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookCommentService extends IService<BookComment> {

    Page<BookComment> pageOf(Page<BookComment> page, Long bookId);

    Page<BookComment> pageOfReader(Page<BookComment> page, Long userId);
}
