package com.albert.godworld.arm.resource.dto;

public enum RVError {

    EMAIL_NOT_AVAIL(101,"邮箱地址已被占用"),
    CAPTCHA_NOT_CORRECT(102,"验证码不正确"),
    USERNAME_NOT_AVAIL(103,"用户名已存在"),
    USER_INSERT_ERROR(104,"用户新增失败"),
    USER_HAS_NO_PERMISSION(105,"用户没有权限操作该接口"),
    USER_NOT_SAME(106,"用户使用的账户与提交的账户不一致"),

    AUTHOR_USER_NOT_SAME(201,"使用的用户与提交的作者不是同一个账号"),
    AUTHOR_ALREADY_BIND(202,"账号已绑定了作者信息"),
    AUTHOR_NAME_UNAVAILABLE(203,"作者名字已存在"),

    UNKNOWN_ERROR(10001,"未知错误"),
    DATABASE_ERROR(10002,"数据库操作失败"),

    SOCIAL_REGISTER_DB_ERROR(301,"创建社团数据库操作错误"),
    SOCIAL_ALREADY_HAS(302,"已经加入了社团"),
    SOCIAL_NAME_NOT_AVAIL(303,"社团名字已占用"),
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
