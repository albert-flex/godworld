package com.albert.godworld.arm.resource.vo.book;

import lombok.Data;
import java.util.Date;

@Data
public class BookCommentVo {
    private Long id;
    private Long bookId;
    private String bookName;
    private Long userId;
    private String userName;
    private String content;
    private Date createTime;
}
