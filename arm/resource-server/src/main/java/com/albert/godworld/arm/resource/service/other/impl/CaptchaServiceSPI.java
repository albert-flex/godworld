package com.albert.godworld.arm.resource.service.other.impl;

import com.albert.godworld.arm.resource.service.other.CaptchaService;
import com.albert.godworld.arm.resource.service.other.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CaptchaServiceSPI implements CaptchaService {

    //captcha - > hash
    private final StringRedisTemplate redisTemplate;
    private final EmailService emailService;
    private final String redisLib = "captcha-";

    @Autowired
    public CaptchaServiceSPI(StringRedisTemplate redisTemplate, EmailService emailService) {
        this.redisTemplate = redisTemplate;
        this.emailService = emailService;
    }

    @Override
    public String createOne() {
        int number= (int) ((Math.random() * 9 + 1) * 100000);
        return "" + number;
    }

    @Override
    public void saveAndSend(String captcha, String email) {
        String emailC = createEmail(email, captcha);
        if (emailService.sendMimeMail(email, "GodWorld 验证码", emailC)) {
            String key = redisLib + email;
            redisTemplate.opsForValue().set(key, captcha);
            redisTemplate.expire(key,5,TimeUnit.MINUTES);
        } else {
            log.error("发送验证码到邮箱:" + email + " 失败.");
        }
    }

    private String createEmail(String email, String captcha) {
        String title = "<h2>GodWorld 验证码</h2>";
        String body = "<p>你的验证码为:" + captcha + "</p>";
        return title + body;
    }

    @Override
    public boolean checkCaptcha(String offer, String email) {
        String key=redisLib+email;
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
            return false;
        }
        String captcha = redisTemplate.opsForValue().get(key);
        return captcha != null && captcha.equals(offer);
    }
}
