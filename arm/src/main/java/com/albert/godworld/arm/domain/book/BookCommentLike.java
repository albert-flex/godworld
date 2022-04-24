package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookCommentLike {

    @TableId
    private Long id;
    private Long bookId;
    private Long authorId;
    private Long commentId;
    private Date likeTime;
}
