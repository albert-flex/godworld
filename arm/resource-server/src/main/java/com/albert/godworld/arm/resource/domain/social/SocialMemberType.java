package com.albert.godworld.arm.resource.domain.social;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum SocialMemberType{
    MASTER(1,"负责人"),
    ADMIN(2,"管理员"),
    NORMAL(3,"普通社员");

    private final Integer code;
    private final String name;

    SocialMemberType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SocialMemberType ofType(Integer co){
        SocialMemberType[] types=SocialMemberType.values();
        for(SocialMemberType t:types){
            if(co.equals(t.code)){
                return t;
            }
        }
        return SocialMemberType.NORMAL;
    }
}
