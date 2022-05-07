package com.albert.godworld.arm.resource.service.other.impl;

import com.albert.godworld.arm.resource.service.other.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
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
    public boolean sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setText(text);
        message.setSubject(subject);
        message.setTo(to);
        try{
            javaMailSender.send(message);
            return true;
        }catch (MailException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendMimeMail(String to, String subject, String text) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(text,true);
            helper.setSubject(subject);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
