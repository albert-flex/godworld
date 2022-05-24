package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.albert.godworld.arm.resource.service.social.RequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SocialRequestHandlerFactory {

    private final List<RequestHandler> handlerList;

    private final RequestCall requestCall;

    private RequestHandler find(SocialRequestType type){
        Optional<RequestHandler> optional=handlerList.stream().filter(t->t.type()==type).findFirst();
        return optional.orElse(null);
    }

    public boolean confirm(SocialRequest request,Long adminMemberId){
        RequestHandler handler=find(SocialRequestType.parse(request.getType()));
        if(handler==null){
            requestCall.setError(RVError.SOCIAL_REQUEST_HANDLER_NOT_FOUND);
            return false;
        }

        return handler.okHandle(request,adminMemberId);
    }

    public boolean refuse(SocialRequest request,Long adminMemberId){
        RequestHandler handler=find(SocialRequestType.parse(request.getType()));
        if(handler==null){
            requestCall.setError(RVError.SOCIAL_REQUEST_HANDLER_NOT_FOUND);
            return false;
        }

        return handler.refuseHandle(request,adminMemberId);
    }
}
