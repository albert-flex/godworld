package com.albert.godworld.arm.controller.social;

import com.albert.godworld.arm.domain.social.SocialInfo;
import com.albert.godworld.arm.service.social.SocialInfoService;
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
