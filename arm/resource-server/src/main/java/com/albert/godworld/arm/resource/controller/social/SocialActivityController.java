package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.social.SocialActivity;
import com.albert.godworld.arm.resource.dto.ActivityDTO;
import com.albert.godworld.arm.resource.service.social.SocialActivityService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social_activity")
@AllArgsConstructor
public class SocialActivityController {

    private final SocialActivityService socialActivityService;

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
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public Boolean create(@RequestBody ActivityDTO activity){
        return socialActivityService.post(activity);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public Boolean update(@RequestBody SocialActivity activity){
        return socialActivityService.updateById(activity);
    }

    @PutMapping("/on/{id}")
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public Boolean on(@PathVariable("id") Long id){
        return socialActivityService.actOn(id);
    }

    @PutMapping("/off/{id}")
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public Boolean off(@PathVariable("id") Long id){
        return socialActivityService.actOff(id);
    }
}
