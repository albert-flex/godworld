package com.albert.godworld.arm.resource.domain.user;

public enum UGroups {
    /**
     * (1,"Admin","管理员"),
     * (2,"Reader","读者"),
     * (3,"Author","作者"),
     * (4,"Social","社员"),
     * (5,"SocialAdmin","社团管理员");
     */
    ADMIN(1,"系统管理员"),
    READER(2,"读者"),
    AUTHOR(3,"作者"),
    SOCIAL(4,"社团成员"),
    SOCIAL_ADMIN(5,"社团管理员")
    ;
    private final int code;
    private final String name;

    UGroups(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
