package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddRequestHandler implements RequestHandler{

    private final SocialMemberService socialMemberService;

    @Override
    public SocialRequestType type() {
        return SocialRequestType.ADD_TO_SOCIAL;
    }

    @Override
    public boolean okHandle(SocialRequest request,Long adminMemberId) {
        return false;
    }

    @Override
    public boolean refuseHandle(SocialRequest request,Long adminMemberId) {
        return false;
    }

}
