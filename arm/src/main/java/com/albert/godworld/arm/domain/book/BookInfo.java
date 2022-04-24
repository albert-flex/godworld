package com.albert.godworld.arm.domain.book;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BookInfo {

    @TableId
    private Long id;
    private Long authorId;
    private Long clickCount;
    private Long boardId;
    private String title;
    private String description;
    private Boolean upShelf;
    private Date createTime;
    private Date updateTime;
    private String updateTitle;
    private Long newChapterId;
}
