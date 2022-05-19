package com.albert.godworld.arm.resource.vo.author;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorNewestVo {

    private Long id;
    private String name;
    private String bookName;
    private Date createTime;
}
