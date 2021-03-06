package com.albert.godworld.arm.resource.dto;

public enum RVError {

    NONE(0,"未设置错误信息"),
    EMAIL_NOT_AVAIL(101,"邮箱地址已被占用"),
    CAPTCHA_NOT_CORRECT(102,"验证码不正确"),
    USERNAME_NOT_AVAIL(103,"用户名已存在"),
    USER_INSERT_ERROR(104,"用户新增失败"),
    USER_HAS_NO_PERMISSION(105,"用户没有权限操作该接口"),
    USER_NOT_SAME(106,"用户使用的账户与提交的账户不一致"),

    AUTHOR_USER_NOT_SAME(201,"使用的用户与提交的作者不是同一个账号"),
    AUTHOR_ALREADY_BIND(202,"账号已绑定了作者信息"),
    AUTHOR_NAME_UNAVAILABLE(203,"作者名字已存在"),
    AUTHOR_NOT_FOUND(204,"无法找到作者"),

    UNKNOWN_ERROR(10001,"未知错误"),
    DATABASE_ERROR(10002,"数据库操作失败"),

    SOCIAL_REGISTER_DB_ERROR(301,"创建社团数据库操作错误"),
    SOCIAL_ALREADY_HAS(302,"已经加入了社团"),
    SOCIAL_NAME_NOT_AVAIL(303,"社团名字已占用"),
    SOCIAL_REQUEST_HANDLE_FAIL(304,"社团请求处理失败"),
    SOCIAL_MEMBER_ALREADY_HAS(305,"已经是社团成员"),
    SOCIAL_REQUEST_HANDLER_NOT_FOUND(306,"未支持的社团请求"),
    SOCIAL_MEMBER_NOT_HAS(307,"不是社团成员"),
    SOCIAL_MEMBER_IS_MASTER(308,"该社员是负责人"),
    SOCIAL_REQUEST_FORMAT_ERROR(309,"社团请求格式错误"),
    SOCIAL_NOT_ADMIN(310,"不是社团管理员"),
    SOCIAL_NOT_NORMAL(311,"不是社团普通成员"),
    SOCIAL_IS_NORMAL(312,"是普通社员"),
    SOCIAL_CHANGE_TYPE_ERROR(313,"社员切换类型出错"),
    SOCIAL_REQUEST_NOT_FOUND(314,"社团请求未找到"),

    BOOK_MODIFY_FAIL(401,"书籍编辑失败"),
    BOOK_VOLUME_NOT_FOUND(402,"卷目找不到"),
    BOOK_CHAPTER_NOT_FOUND(403,"章节找不到"),
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
