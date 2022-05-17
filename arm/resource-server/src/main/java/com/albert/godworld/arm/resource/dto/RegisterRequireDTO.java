package com.albert.godworld.arm.resource.dto;

import com.albert.godworld.arm.resource.domain.user.User;
import lombok.Data;

@Data
public class RegisterRequireDTO {

    private String userName;
    private String password;
    private String email;
    private String captcha;

    public User convert(){
        User user=new User();
        user.setUsername(userName);
        user.setEmail(email);
        return user;
    }
}
