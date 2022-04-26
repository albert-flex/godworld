package com.albert.godworld.arm.service.social.impl;

import com.albert.godworld.arm.domain.social.SocialInfo;
import com.albert.godworld.arm.mapper.social.SocialInfoMapper;
import com.albert.godworld.arm.service.social.SocialInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SocialInfoServiceSPI extends ServiceImpl<SocialInfoMapper, SocialInfo>
    implements SocialInfoService{

}
