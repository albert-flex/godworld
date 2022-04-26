package com.albert.godworld.arm.service.social.impl;

import com.albert.godworld.arm.domain.social.SocialRecord;
import com.albert.godworld.arm.domain.social.SocialRequest;
import com.albert.godworld.arm.mapper.social.SocialRecordMapper;
import com.albert.godworld.arm.service.social.SocialRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SocialRecordServiceSPI extends ServiceImpl<SocialRecordMapper, SocialRecord>
    implements SocialRecordService {

    @Override
    public Page<SocialRecord> pageOf(Page<SocialRecord> page, Long socialId) {
        QueryWrapper<SocialRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("social_id",socialId);
        return super.page(page,queryWrapper);
    }

    @Override
    public void putRecord(SocialRequest request) {
        SocialRecord socialRecord=new SocialRecord();
        socialRecord.setSocialId(request.getSocialId());
        socialRecord.setType(request.getType());
        socialRecord.setTime(request.getHandledTime());
        socialRecord.setContent(request.getContent());
        super.save(socialRecord);
    }
}
