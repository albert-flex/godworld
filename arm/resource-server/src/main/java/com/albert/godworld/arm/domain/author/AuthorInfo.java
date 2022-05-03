package com.albert.godworld.arm.domain.author;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorInfo {

    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String moto;

    private Date createTime;
}
