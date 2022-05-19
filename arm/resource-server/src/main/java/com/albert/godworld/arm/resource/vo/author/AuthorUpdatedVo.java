package com.albert.godworld.arm.resource.vo.author;

import lombok.Data;

@Data
public class AuthorUpdatedVo {

    private Long id;
    private String authorName;
    private String bookName;
    private String bookVolume;
    private String chapter;
}
