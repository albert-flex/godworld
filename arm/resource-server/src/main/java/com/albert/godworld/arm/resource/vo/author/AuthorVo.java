package com.albert.godworld.arm.resource.vo.author;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorVo {
    private Long id;
    private String authorName;
    private Long presentBookId;
    private String presentBookName;
    private Date inTime;
    private Date updateTime;
    private Long updateBookId;
    private String updateBookName;
    private String updateVolume;
    private Long updateChapterId;
    private String updateChapter;
    private Long socialId;
    private String socialName;
    private String moto;
}
