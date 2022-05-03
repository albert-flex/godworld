package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.social.SocialAnnounce;
import com.albert.godworld.arm.resource.mapper.social.SocialAnnounceMapper;
import com.albert.godworld.arm.resource.service.social.SocialAnnounceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SocialAnnounceServiceSPI extends ServiceImpl<SocialAnnounceMapper, SocialAnnounce>
        implements SocialAnnounceService {

    @Override
    public Page<SocialAnnounce> ofSocial(Page<SocialAnnounce> page, Long socialId) {
        LambdaQueryWrapper<SocialAnnounce> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialAnnounce::getSocialId, socialId);
        return super.page(page, queryWrapper);
    }
}
