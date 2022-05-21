package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.service.social.SocialInfoService;
import com.albert.godworld.arm.resource.vo.social.SocialInfoVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewActVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewAnnVo;
import com.albert.godworld.arm.resource.vo.social.SocialReVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social")
public class SocialInfoController {

    private final SocialInfoService socialInfoService;

    @Autowired
    public SocialInfoController(SocialInfoService socialInfoService) {
        this.socialInfoService = socialInfoService;
    }

    @PostMapping
    public SocialInfo create(@RequestBody SocialInfo socialInfo){
        socialInfoService.save(socialInfo);
        return socialInfo;
    }

    @PutMapping
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
