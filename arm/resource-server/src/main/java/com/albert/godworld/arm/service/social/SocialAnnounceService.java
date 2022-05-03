package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialAnnounce;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialAnnounceService extends IService<SocialAnnounce> {

    Page<SocialAnnounce> ofSocial(Page<SocialAnnounce> page,Long socialId);
}
