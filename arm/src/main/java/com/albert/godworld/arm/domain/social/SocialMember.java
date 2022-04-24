package com.albert.godworld.arm.domain.social;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SocialMember {

    @TableId
    private Long id;
    private Long socialId;
    private Long authorId;
    private Date timeIn;
    private SocialMemberType type;
}
