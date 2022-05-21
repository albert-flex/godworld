package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.mapper.social.SocialInfoMapper;
import com.albert.godworld.arm.resource.service.social.SocialInfoService;
import com.albert.godworld.arm.resource.vo.social.SocialInfoVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewActVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewAnnVo;
import com.albert.godworld.arm.resource.vo.social.SocialReVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public Page<SocialNewActVo> newActPage(Page<SocialNewActVo> page) {
        return super.baseMapper.newActPage(page);
    }

    @Override
    public Page<SocialNewAnnVo> newAnnPage(Page<SocialNewAnnVo> page) {
        return super.baseMapper.newAnnPage(page);
    }

    @Override
    public Page<SocialReVo> query(Page<SocialReVo> page, String name) {
        return super.baseMapper.query(page,name);
    }

    @Override
    public SocialInfoVo getInfoById(Long id) {
        return super.baseMapper.getById(id);
    }
}
