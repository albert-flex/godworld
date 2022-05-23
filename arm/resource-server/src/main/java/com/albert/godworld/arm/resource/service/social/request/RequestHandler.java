package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;

public interface RequestHandler {

    SocialRequestType type();
    boolean okHandle(SocialRequest request,Long adminMemberId);
    boolean refuseHandle(SocialRequest request,Long adminMemberId);
}
