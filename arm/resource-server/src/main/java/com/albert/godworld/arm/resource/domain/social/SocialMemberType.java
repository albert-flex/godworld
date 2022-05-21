package com.albert.godworld.arm.resource.domain.social;

import com.baomidou.mybatisplus.annotation.IEnum;
import org.springframework.security.core.parameters.P;

public enum SocialMemberType implements IEnum<Integer>{
    MASTER(1,"负责人"),
    ADMIN(2,"管理员"),
    NORMAL(3,"普通社员");

    private final int code;
    private final String name;

    SocialMemberType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    public static SocialMemberType ofType(Integer co){
        SocialMemberType[] types=SocialMemberType.values();
        for(SocialMemberType t:types){
            if(t.code==co){
                return t;
            }
        }
        return SocialMemberType.NORMAL;
    }

    public String getName(){
        return name;
    }
}
