package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.domain.user.Permissions;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.dto.SocialRegVo;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.other.CaptchaService;
import com.albert.godworld.arm.resource.service.social.SocialInfoService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.social.SocialInfoVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewActVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewAnnVo;
import com.albert.godworld.arm.resource.vo.social.SocialReVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/social")
@AllArgsConstructor
public class SocialInfoController {

    private final SocialInfoService socialInfoService;
    private final PrincipalConvert convert;
    private final AuthorService authorService;
    private final CaptchaService captchaService;

    @PostMapping
    @PreAuthorize("hasAuthority('AUTHOR_PER')")
    public RV<SocialInfo> create(@RequestBody SocialRegVo socialInfo, Principal principal){
        User user=convert.convert(principal);

        if(!Permissions.ADMIN_PER.hasIn(user)){
            AuthorInfo author=authorService.getById(socialInfo.getMasterId());
            if(!author.getUserId().equals(user.getId())){
                RVError.AUTHOR_USER_NOT_SAME.to();
            }
        }

        String email=user.getEmail();
        if(!captchaService.checkCaptcha(socialInfo.getCaptcha(),email)){
            return RVError.CAPTCHA_NOT_CORRECT.to();
        }

        SocialInfo info=new SocialInfo();
        info.setMasterId(user.getId());
        info.setMoto("moto");
        info.setName(socialInfo.getName());

        return socialInfoService.register(info);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public Boolean update(@RequestBody SocialInfo socialInfo){
        return socialInfoService.updateById(socialInfo);
    }

    @GetMapping("/id/{id}")
    public SocialInfo getById(@PathVariable("id") Long id){
        return socialInfoService.getById(id);
    }

    @GetMapping("/info/{id}")
    public SocialInfoVo getInfo(@PathVariable("id") Long id){
        return socialInfoService.getInfoById(id);
    }

    @GetMapping("/new_ann")
    public Page<SocialNewAnnVo> newAnn(Page<SocialNewAnnVo> page){
        return socialInfoService.newAnnPage(page);
    }

    @GetMapping("/new_act")
    public Page<SocialNewActVo> newAct(Page<SocialNewActVo> page){
        return socialInfoService.newActPage(page);
    }

    @GetMapping("/query/{name}")
    public Page<SocialReVo> query(Page<SocialReVo> page,@PathVariable("name") String name){
        return socialInfoService.query(page,name);
    }

    @GetMapping("/page/all")
    public Page<SocialInfo> page(Page<SocialInfo> page){
        return socialInfoService.page(page);
    }

    @GetMapping("/get_by/master/{masterId}")
    public SocialInfo getByMasterId(@PathVariable("masterId") Long masterId){
        return socialInfoService.getByMaster(masterId);
    }

    @GetMapping("/author/{authorId}")
    public SocialInfo getByAuthor(@PathVariable("authorId") Long authorId){
        return socialInfoService.getByAuthor(authorId);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return socialInfoService.removeById(id);
    }
}
