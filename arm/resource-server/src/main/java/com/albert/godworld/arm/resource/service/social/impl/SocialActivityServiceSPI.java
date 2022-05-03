package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.social.SocialActivity;
import com.albert.godworld.arm.resource.mapper.social.SocialActivityMapper;
import com.albert.godworld.arm.resource.service.social.SocialActivityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SocialActivityServiceSPI extends ServiceImpl<SocialActivityMapper, SocialActivity>
        implements SocialActivityService {

    @Override
    public Page<SocialActivity> ofSocial(Page<SocialActivity> page, Long socialId, Boolean on) {
        LambdaQueryWrapper<SocialActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialActivity::getSocialId, socialId);
        if (on != null) {
            queryWrapper.eq(SocialActivity::getActOn, on);
        }
        return super.page(page, queryWrapper);
    }

    @Override
    public Page<SocialActivity> ofHoner(Page<SocialActivity> page, Long honerId) {
        LambdaQueryWrapper<SocialActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialActivity::getHonerAuthorId, honerId);
        return super.page(page, queryWrapper);
    }

    @Override
    public boolean actOn(Long activityId) {
        return _setAct(activityId, Boolean.TRUE);
    }

    private boolean _setAct(Long activityId, Boolean on) {
        LambdaUpdateWrapper<SocialActivity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SocialActivity::getId, activityId);
        updateWrapper.set(SocialActivity::getActOn, on);
        return super.update(updateWrapper);
    }

    @Override
    public boolean actOff(Long activityId) {
        return _setAct(activityId, Boolean.FALSE);
    }
}
