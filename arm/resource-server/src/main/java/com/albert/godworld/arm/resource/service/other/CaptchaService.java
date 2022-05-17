package com.albert.godworld.arm.resource.service.other;

public interface CaptchaService {

    String createOne();
    boolean saveAndSend(String captcha,String email);
    boolean checkCaptcha(String offer,String email);
}
