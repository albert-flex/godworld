package com.albert.godworld.arm.controller.social;

import com.albert.godworld.arm.domain.social.SocialAnnounce;
import com.albert.godworld.arm.service.social.SocialAnnounceService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social_announce")
public class SocialAnnounceController {

    private final SocialAnnounceService socialAnnounceService;

    @Autowired
    public SocialAnnounceController(SocialAnnounceService socialAnnounceService) {
        this.socialAnnounceService = socialAnnounceService;
    }

    @PostMapping
    public SocialAnnounce create(@RequestBody SocialAnnounce announce){
        socialAnnounceService.save(announce);
        return announce;
    }

    @PutMapping
    public Boolean update(@RequestBody SocialAnnounce announce){
        return socialAnnounceService.updateById(announce);
    }

    @GetMapping("/page/social/{socialId}")
    public Page<SocialAnnounce> ofSocial(@PathVariable("socialId") Long socialId,Page<SocialAnnounce> page){
        return socialAnnounceService.ofSocial(page,socialId);
    }
}
