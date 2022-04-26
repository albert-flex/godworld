package com.albert.godworld.arm.service.social.impl;

import com.albert.godworld.arm.domain.social.SocialActivity;
import com.albert.godworld.arm.domain.social.SocialActivityState;
import com.albert.godworld.arm.domain.social.SocialInfo;
import com.albert.godworld.arm.mapper.social.SocialActivityMapper;
import com.albert.godworld.arm.service.social.SocialActivityService;
import com.albert.godworld.arm.service.social.SocialInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialActivityServiceSPI extends ServiceImpl<SocialActivityMapper, SocialActivity>
    implements SocialActivityService {

    private final SocialInfoService socialInfoService;

    @Autowired
    public SocialActivityServiceSPI(SocialInfoService socialInfoService) {
        this.socialInfoService = socialInfoService;
    }

    @Override
    public Page<SocialActivity> pageOf(Page<SocialActivity> page, Long socialId) {
        QueryWrapper<SocialActivity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("social_id",socialId);
        return super.page(page,queryWrapper);
    }

    @Override
    public boolean start(Long activityId) {
        SocialActivity activity=super.getById(activityId);
        if(activity==null||activity.getState()!= SocialActivityState.UN_STARTED)return false;

        activity.setState(SocialActivityState.UNDER_GOING);
        return super.updateById(activity);
    }

    @Override
    public boolean end(Long activityId) {
        SocialActivity activity=super.getById(activityId);
        if(activity==null||activity.getState()!= SocialActivityState.UNDER_GOING)return false;

        activity.setState(SocialActivityState.ENDED);
        return super.updateById(activity);
    }

    @Override
    public boolean setTop(Long activityId) {
        SocialActivity activity=super.getById(activityId);
        if(activity==null)return false;

        SocialInfo info=socialInfoService.getById(activity.getSocialId());
        if(!info.getTopActivityId().equals(activityId)){
            info.setTopActivityId(activityId);
            return socialInfoService.updateById(info);
        }
        return false;
    }
}
