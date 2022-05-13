package com.albert.godworld.arm.resource.dto;

import lombok.Data;

@Data
public class UserResponse {

    private String userId;
    private String userName;
    private String email;
    private Long authorId;
    private Long socialId;
}