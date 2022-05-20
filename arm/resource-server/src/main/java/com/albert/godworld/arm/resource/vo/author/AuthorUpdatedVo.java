package com.albert.godworld.arm.resource.vo.author;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorUpdatedVo {

    private Long id;
    private String authorName;
    private String presentBook;
    private String bookName;
    private String socialName;
    private String volumeName;
    private String chapter;
    private String moto;
    private Date updateTime;
}
