package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialInfoService extends IService<SocialInfo> {

    SocialInfo getByMaster(Long masterId);
    SocialInfo getByAuthor(Long authorId);
}
