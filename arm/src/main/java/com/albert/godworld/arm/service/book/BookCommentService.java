package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookComment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookCommentService extends IService<BookComment> {

    //publish / remove / like / un like comment.
    boolean like(Long commentId);
    boolean unlike(Long commentId);

    Page<BookComment> likeOf(Page<BookComment> page,Long bookId);
}
