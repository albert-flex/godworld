package com.albert.godworld.arm.resource.controller;

import com.albert.godworld.arm.resource.service.other.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    @Autowired
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @PostMapping
    public void saveAndSend(@RequestParam("email") String email){
        String captcha=captchaService.createOne();
        captchaService.saveAndSend(captcha,email);
    }

    @GetMapping
    public Boolean checkCaptcha(@RequestParam("email") String email,
                                @RequestParam("captcha") String captcha){
        return captchaService.checkCaptcha(captcha,email);
    }
}
