package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SocialRequestHandlerFactory {

    private final List<RequestHandler> handlerList;

    private RequestHandler find(SocialRequestType type){
        Optional<RequestHandler> optional=handlerList.stream().filter(t->t.type()==type).findFirst();
        return optional.orElse(null);
    }

    public boolean confirm(SocialRequest request,Long adminMemberId){
        RequestHandler handler=find(SocialRequestType.parse(request.getType()));
        if(handler==null)return false;

        return handler.okHandle(request,adminMemberId);
    }

    public boolean refuse(SocialRequest request,Long adminMemberId){
        RequestHandler handler=find(SocialRequestType.parse(request.getType()));
        if(handler==null)return false;

        return handler.refuseHandle(request,adminMemberId);
    }
}
