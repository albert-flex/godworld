package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.social.SocialActivity;
import com.albert.godworld.arm.resource.service.social.SocialActivityService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social_activity")
public class SocialActivityController {

    private final SocialActivityService socialActivityService;

    @Autowired
    public SocialActivityController(SocialActivityService socialActivityService) {
        this.socialActivityService = socialActivityService;
    }

    @GetMapping("/page/all")
    public Page<SocialActivity> all(Page<SocialActivity> page) {
        return socialActivityService.page(page);
    }

    @GetMapping("/page/social/{socialId}/{on}")
    public Page<SocialActivity> ofSocialOn(@PathVariable("socialId") Long socialId, Page<SocialActivity> page,
                                           @PathVariable("on") Boolean on) {
        return socialActivityService.ofSocial(page, socialId, on);
    }

    @GetMapping("/page/social/{socialId}")
    public Page<SocialActivity> ofSocialOff(@PathVariable("socialId") Long socialId, Page<SocialActivity> page) {
        return socialActivityService.ofSocial(page, socialId, null);
    }

    @GetMapping("/page/honer/{honerId}")
    public Page<SocialActivity> ofHoner(@PathVariable("honerId") Long honerId,Page<SocialActivity> page){
        return socialActivityService.ofHoner(page,honerId);
    }

    @PostMapping
    public SocialActivity create(@RequestBody SocialActivity activity){
        socialActivityService.save(activity);
        return activity;
    }

    @PutMapping
    public Boolean update(@RequestBody SocialActivity activity){
        return socialActivityService.updateById(activity);
    }

    @PutMapping("/on/{id}")
    public Boolean on(@PathVariable("id") Long id){
        return socialActivityService.actOn(id);
    }

    @PutMapping("/off/{id}")
    public Boolean off(@PathVariable("id") Long id){
        return socialActivityService.actOn(id);
    }
}
