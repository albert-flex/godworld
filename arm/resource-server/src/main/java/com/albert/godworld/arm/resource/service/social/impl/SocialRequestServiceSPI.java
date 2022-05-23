package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.service.social.request.SocialRequestHandlerFactory;
import com.albert.godworld.arm.resource.mapper.social.SocialRequestMapper;
import com.albert.godworld.arm.resource.service.social.SocialRequestService;
import com.albert.godworld.arm.resource.vo.social.SocialRequestVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocialRequestServiceSPI extends ServiceImpl<SocialRequestMapper,SocialRequest> implements SocialRequestService {

    private final SocialRequestHandlerFactory factory;

    @Override
    public Page<SocialRequestVo> page(Page<SocialRequestVo> page, Long socialId) {
        return super.baseMapper.getBySocialId(page,socialId);
    }

    @Override
    public boolean requestConfirm(Long requestId, Long adminMemberId) {
        SocialRequest request=super.getById(requestId);
        if(request==null)return false;

        return factory.confirm(request,adminMemberId);
    }

    @Override
    public boolean requestRefuse(Long requestId, Long adminMemberId) {
        SocialRequest request=super.getById(requestId);
        if(request==null)return false;

        return factory.refuse(request,adminMemberId);
    }
}
