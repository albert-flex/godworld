package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.social.SocialAnnounce;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.service.social.SocialAnnounceService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.social.AnnounceVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/social_announce")
@AllArgsConstructor
public class SocialAnnounceController {

    private final SocialAnnounceService socialAnnounceService;

    @PostMapping
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public SocialAnnounce create(@RequestBody SocialAnnounce announce, Principal principal){
        socialAnnounceService.save(announce);
        return announce;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public Boolean update(@RequestBody SocialAnnounce announce){
        return socialAnnounceService.updateById(announce);
    }

    @GetMapping("/page/social/{socialId}")
    public Page<AnnounceVo> ofSocial(@PathVariable("socialId") Long socialId, Page<AnnounceVo> page){
        return socialAnnounceService.ofSocial(page,socialId);
    }
}
