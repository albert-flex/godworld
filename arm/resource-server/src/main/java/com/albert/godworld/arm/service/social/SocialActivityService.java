package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialActivity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialActivityService extends IService<SocialActivity> {

    Page<SocialActivity> ofSocial(Page<SocialActivity> page,Long socialId,Boolean on);
    Page<SocialActivity> ofHoner(Page<SocialActivity> page,Long honerId);
    boolean actOn(Long activityId);
    boolean actOff(Long activityId);
}
