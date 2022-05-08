package com.albert.godworld.arm.resource.dto;

public enum RVError {

    EMAIL_NOT_AVAIL(101,"邮箱地址已被占用"),
    CAPTCHA_NOT_CORRECT(102,"验证码不正确"),
    USERNAME_NOT_AVAIL(103,"用户名已存在"),
    USER_INSERT_ERROR(104,"用户新增失败"),


    ;
    int code;
    String msg;
    RVError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public <T> RV<T> to(){
        return RV.error(this);
    }
}
