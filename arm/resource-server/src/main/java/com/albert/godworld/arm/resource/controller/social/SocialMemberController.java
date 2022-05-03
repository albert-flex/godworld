package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social/member")
public class SocialMemberController {

    private final SocialMemberService socialMemberService;

    @Autowired
    public SocialMemberController(SocialMemberService socialMemberService) {
        this.socialMemberService = socialMemberService;
    }

    @PostMapping("/in/{authorId}/{name}")
    public Boolean in(@PathVariable("authorId") Long authorId,@PathVariable("name") String name){
        return socialMemberService.in(authorId, name);
    }

    @PostMapping("/out/{authorId}")
    public Boolean out(@PathVariable("authorId") Long authorId){
        return socialMemberService.out(authorId);
    }

    @GetMapping("/page/all")
    public Page<SocialMember> _page(Page<SocialMember> page){
        return socialMemberService.page(page);
    }

    @GetMapping("/social/{socialId}")
    public Page<SocialMember> pageOfSocial(@PathVariable("socialId") Long socialId,Page<SocialMember> page){
        return socialMemberService.memberOfSocial(page,socialId);
    }

    @GetMapping("/page/social/{socialId}/{type}")
    public Page<SocialMember> pageOfSocialType(@PathVariable("socialId") Long socialId,@PathVariable("type") String type,Page<SocialMember> page){
        return socialMemberService.memberOfType(page,socialId, SocialMemberType.valueOf(type));
    }
}
