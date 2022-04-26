package com.albert.godworld.arm.service.social.impl;

import com.albert.godworld.arm.domain.social.SocialRequest;
import com.albert.godworld.arm.domain.social.SocialRequestState;
import com.albert.godworld.arm.domain.social.SocialRequestType;
import com.albert.godworld.arm.mapper.social.SocialRequestMapper;
import com.albert.godworld.arm.service.social.SocialRecordService;
import com.albert.godworld.arm.service.social.SocialRequestService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SocialRequestServiceSPI extends ServiceImpl<SocialRequestMapper, SocialRequest>
        implements SocialRequestService {

    private final SocialRecordService socialRecordService;

    @Autowired
    public SocialRequestServiceSPI(SocialRecordService socialRecordService) {
        this.socialRecordService = socialRecordService;
    }

    @Override
    public Page<SocialRequest> pageOf(Page<SocialRequest> page, Long socialId, SocialRequestState state) {
        return pageOf(page, socialId, null, state);
    }

    @Override
    public Page<SocialRequest> pageOf(Page<SocialRequest> page, Long socialId, SocialRequestType type, SocialRequestState state) {
        QueryWrapper<SocialRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("social_id", socialId);
        if (state != null) {
            queryWrapper.eq("state", state);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        return super.page(page, queryWrapper);
    }

    @Override
    public void putRequest(Long socialId, SocialRequestType type, Long requestAuthorId, Long destId) {
        SocialRequest request = new SocialRequest();
        request.setSocialId(socialId);
        request.setType(type);
        request.setRequestAuthorId(requestAuthorId);
        request.setContent(type.name());
        request.setState(SocialRequestState.UNHANDLED);
        request.setDestId(destId);
        request.setTime(new Date());

        super.save(request);
    }

    @Override
    public boolean confirm(Long requestId, Long handlerId) {
        SocialRequest socialRequest = super.getById(requestId);
        if (socialRequest == null || socialRequest.getState() != SocialRequestState.UNHANDLED) return false;

        socialRequest.setState(SocialRequestState.CONFIRM);
        socialRequest.setHandledTime(new Date());
        boolean result = super.updateById(socialRequest);
        if(result){
            socialRecordService.putRecord(socialRequest);
        }
        return result;
    }

    @Override
    public boolean refuse(Long requestId, Long handlerId) {
        SocialRequest socialRequest = super.getById(requestId);
        if (socialRequest == null || socialRequest.getState() != SocialRequestState.UNHANDLED) return false;

        socialRequest.setState(SocialRequestState.REFUSE);
        socialRequest.setHandledTime(new Date());
        boolean result = super.updateById(socialRequest);
        if(result){
            socialRecordService.putRecord(socialRequest);
        }
        return result;
    }
}
