package com.albert.godworld.arm.resource.service.email.impl;

import com.albert.godworld.arm.resource.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceSPI implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceSPI(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setText(text);
        message.setSubject(subject);
        message.setTo(to);
        javaMailSender.send(message);
    }

    @Override
    public void sendMimeMail(String to, String subject, String text) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(text,true);
            helper.setSubject(subject);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
