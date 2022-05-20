package com.albert.godworld.arm.resource.domain.user;

public enum Permissions {
    ADMIN_PER(1, "系统管理员"),//管理员权限
    USER_PER(2, "读者权限"),//用户权限
    AUTHOR_PER(3, "作者权限"),//作者权限
    SOCIAL_PER(4, "社员权限"),//社团成员权限
    SOCIAL_ADMIN_PER(5, "社团管理员权限"),//社团管理权限
    ;
    private final int code;
    private final String name;

    Permissions(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public boolean hasIn(User user){
        return user.hasPermission(this.name());
    }
}
