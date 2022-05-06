package com.albert.godworld.arm.resource.service.email;

public interface EmailService {

    void sendSimpleMail(String to,String subject,String text);
    void sendMimeMail(String to,String subject,String text);
}
