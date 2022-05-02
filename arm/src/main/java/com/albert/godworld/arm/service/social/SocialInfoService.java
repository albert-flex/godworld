package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialInfoService extends IService<SocialInfo> {

    SocialInfo getByMaster(Long masterId);
    SocialInfo getByAuthor(Long authorId);
}
