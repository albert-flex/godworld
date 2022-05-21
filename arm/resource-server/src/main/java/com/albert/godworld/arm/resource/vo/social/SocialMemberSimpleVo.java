package com.albert.godworld.arm.resource.vo.social;

import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import lombok.Data;

@Data
public class SocialMemberSimpleVo {
    private Long id;
    private Long socialId;
    private String memberName;
    private SocialMemberType type;
    private String authorName;
    private Long presetBookId;
    private String presentBookName;
}
