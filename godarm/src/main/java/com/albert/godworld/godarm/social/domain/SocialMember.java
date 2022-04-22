package com.albert.godworld.godarm.social.domain;

import java.util.Date;

public class SocialMember {

    private Long socialId;
    private Long authorId;
    private Date timeIn;
    private SocialMemberType type;

    public Long getSocialId() {
        return socialId;
    }

    public void setSocialId(Long socialId) {
        this.socialId = socialId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public SocialMemberType getType() {
        return type;
    }

    public void setType(SocialMemberType type) {
        this.type = type;
    }
}
