package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.mapper.social.SocialInfoMapper;
import com.albert.godworld.arm.resource.service.social.SocialInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SocialInfoServiceSPI extends ServiceImpl<SocialInfoMapper, SocialInfo>
    implements SocialInfoService {

    @Override
    public SocialInfo getByMaster(Long masterId) {
        LambdaQueryWrapper<SocialInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialInfo::getMasterId,masterId);
        return super.getOne(queryWrapper);
    }

    @Override
    public SocialInfo getByAuthor(Long authorId) {
        return baseMapper.byAuthorId(authorId);
    }
}
