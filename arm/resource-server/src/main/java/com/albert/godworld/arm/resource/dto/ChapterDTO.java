package com.albert.godworld.arm.resource.dto;

import lombok.Data;

@Data
public class ChapterDTO {

    private Long bookId;
    private Long volumeId;
    private String title;
    private String content;
    private Long prevChapterId;
}
