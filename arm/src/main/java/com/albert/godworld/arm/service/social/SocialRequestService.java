package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialRequest;
import com.albert.godworld.arm.domain.social.SocialRequestState;
import com.albert.godworld.arm.domain.social.SocialRequestType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialRequestService extends IService<SocialRequest> {

    Page<SocialRequest> pageOf(Page<SocialRequest> page, Long socialId, SocialRequestState state);
    Page<SocialRequest> pageOf(Page<SocialRequest> page, Long socialId, SocialRequestType type,SocialRequestState state);

    void putRequest(Long socialId,SocialRequestType type,Long requestAuthorId,Long destId);

    boolean confirm(Long requestId,Long handlerId);

    boolean refuse(Long requestId,Long handlerId);
}
