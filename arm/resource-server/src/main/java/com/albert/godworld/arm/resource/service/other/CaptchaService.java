package com.albert.godworld.arm.resource.service.other;

public interface CaptchaService {

    String createOne();
    void saveAndSend(String captcha,String email);
    boolean checkCaptcha(String offer,String email);
}
