package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.albert.godworld.arm.resource.service.social.RequestHandler;
import com.albert.godworld.arm.resource.service.social.SocialActivityBookService;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveActivityRequestHandler implements RequestHandler {

    private final SocialMemberService socialMemberService;
    private final SocialActivityBookService activityBookService;
    private final RequestCall requestCall;

    @Override
    public SocialRequestType type() {
        return SocialRequestType.QUIT_ACTIVITY;
    }

    /**
     * content: activityId;bookId
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
        if(!activityBookService.removeBook(bookId,activityId)){
            requestCall.setError(RVError.SOCIAL_REQUEST_HANDLE_FAIL);
            return false;
        }

        return true;
    }
}
