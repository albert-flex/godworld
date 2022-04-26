package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialActivity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialActivityService extends IService<SocialActivity> {

    Page<SocialActivity> pageOf(Page<SocialActivity> page,Long socialId);

    boolean start(Long activityId);

    boolean end(Long activityId);

    boolean setTop(Long activityId);
}
