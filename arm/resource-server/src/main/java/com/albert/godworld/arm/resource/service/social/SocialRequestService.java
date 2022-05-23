package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.vo.social.SocialRequestVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialRequestService extends IService<SocialRequest> {

    Page<SocialRequestVo> page(Page<SocialRequestVo> page, Long socialId);
    boolean requestConfirm(Long requestId,Long adminMemberId);
    boolean requestRefuse(Long requestId,Long adminMemberId);
}
