package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialAnnounce;
import com.albert.godworld.arm.resource.vo.social.AnnounceVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialAnnounceService extends IService<SocialAnnounce> {

    Page<AnnounceVo> ofSocial(Page<AnnounceVo> page, Long socialId);
}
