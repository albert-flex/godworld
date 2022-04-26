package com.albert.godworld.arm.service.social;

import com.albert.godworld.arm.domain.social.SocialRecord;
import com.albert.godworld.arm.domain.social.SocialRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SocialRecordService extends IService<SocialRecord> {

    Page<SocialRecord> pageOf(Page<SocialRecord> page,Long socialId);

    void putRecord(SocialRequest request);
}
