package com.albert.godworld.arm.resource.service.other;

public interface EmailService {

    boolean sendSimpleMail(String to,String subject,String text);
    boolean sendMimeMail(String to,String subject,String text);
}
