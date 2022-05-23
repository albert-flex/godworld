package com.albert.godworld.arm.resource.domain.social;

public enum SocialRequestType {

    ADD_TO_SOCIAL(1,"加入社团"),
    QUIT_SOCIAL(2,"退出社团"),

    ADD_TO_ACTIVITY(3,"参加活动"),
    QUIT_ACTIVITY(4,"退出活动"),

    SET_ADMIN(5,"成为管理员"),
    QUIT_ADMIN(6,"退出管理员"),

    BANG(7,"解散社团"),
    ;
    final int cde;
    final private String description;

    SocialRequestType(int cde, String description) {
        this.cde = cde;
        this.description = description;
    }

    public static SocialRequestType parse(int code){
        SocialRequestType[] types=SocialRequestType.values();
        for(SocialRequestType t:types){
            if(t.cde==code)return t;
        }
        return ADD_TO_SOCIAL;
    }
}
