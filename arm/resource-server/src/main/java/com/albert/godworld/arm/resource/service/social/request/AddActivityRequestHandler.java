package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.albert.godworld.arm.resource.service.social.RequestHandler;
import com.albert.godworld.arm.resource.service.social.SocialActivityBookService;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@AllArgsConstructor
public class AddActivityRequestHandler implements RequestHandler {

    private final RequestCall requestCall;
    private final SocialActivityBookService activityBookService;
    private final SocialMemberService socialMemberService;

    @Override
    public SocialRequestType type() {
        return SocialRequestType.ADD_TO_ACTIVITY;
    }

    /**
     * content: activityId;bookId
     * @param request
     * @param adminMemberId
     * @return
     */
    @Override
    public boolean okHandle(SocialRequest request, Long adminMemberId) {
        if(!socialMemberService.isMember(request.getAuthorId())){
            requestCall.setError(RVError.SOCIAL_MEMBER_NOT_HAS);
            return false;
        }

        //activityId;bookId
        String content=request.getContent();
        String[] s=content.split(";");
        if(s.length!=2){
            requestCall.setError(RVError.SOCIAL_REQUEST_FORMAT_ERROR);
            return false;
        }

        Long activityId=Long.parseLong(s[0]);
        Long bookId=Long.parseLong(s[1]);
        if(!activityBookService.addBook(bookId,activityId)){
            requestCall.setError(RVError.SOCIAL_REQUEST_HANDLE_FAIL);
            return false;
        }

        return true;
    }
}
