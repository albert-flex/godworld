package com.albert.godworld.arm.domain.social;


import lombok.Data;

@Data
public class SocialMember {

    private Long id;
    private Long socialId;
    private Long authorId;
    private String memberName;
    private SocialMemberType type;
    private Data inTime;
}
