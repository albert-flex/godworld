package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookComment;
import com.albert.godworld.arm.resource.vo.book.BookCommentVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookCommentService extends IService<BookComment> {

    Page<BookCommentVo> pageOf(Page<BookCommentVo> page, Long bookId);

    Page<BookCommentVo> pageOfReader(Page<BookCommentVo> page, Long userId);
}
