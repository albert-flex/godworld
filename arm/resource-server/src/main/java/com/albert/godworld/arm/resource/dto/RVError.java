package com.albert.godworld.arm.resource.dto;

public enum RVError {

    EMAIL_NOT_AVAIL(101,"邮箱地址已被占用"),
    CAPTCHA_NOT_CORRECT(102,"验证码不正确"),
    USERNAME_NOT_AVAIL(103,"用户名已存在"),
    USER_INSERT_ERROR(104,"用户新增失败"),
    USER_HAS_NO_PERMISSION(105,"用户没有权限操作该接口"),
    USER_NOT_SAME(106,"用户使用的账户与提交的账户不一致"),

    AUTHOR_USER_NOT_SAME(201,"使用的用户与提交的作者不是同一个账号"),

    ;
    final int code;
    final String msg;
    RVError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public <T> RV<T> to(){
        return RV.error(this);
    }
}
