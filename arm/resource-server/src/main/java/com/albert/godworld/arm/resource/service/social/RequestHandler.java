package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;

public interface RequestHandler {

    SocialRequestType type();
    boolean okHandle(SocialRequest request,Long adminMemberId);
}
