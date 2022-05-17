package com.albert.godworld.arm.resource.domain.user;

public enum Permissions {
    ADMIN_PER,//管理员权限

    USER_PER,//用户权限

    AUTHOR_PER,//作者权限

    SOCIAL_PER,//社团成员权限
    SOCIAL_ADMIN_PER,//社团管理权限
    ;
    public boolean hasIn(User user){
        return user.hasPermission(this.name());
    }
}
