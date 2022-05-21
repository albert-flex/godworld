package com.albert.godworld.arm.resource.service.social;

import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.vo.social.SocialInfoVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewActVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewAnnVo;
import com.albert.godworld.arm.resource.vo.social.SocialReVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface SocialInfoService extends IService<SocialInfo> {

    SocialInfo getByMaster(Long masterId);
    SocialInfo getByAuthor(Long authorId);

    Page<SocialNewActVo> newActPage(Page<SocialNewActVo> page);
    Page<SocialNewAnnVo> newAnnPage(Page<SocialNewAnnVo> page);
    Page<SocialReVo> query(Page<SocialReVo> page,@Param("name") String name);
    SocialInfoVo getInfoById(Long id);
    RV<SocialInfo> register(SocialInfo social);
}
