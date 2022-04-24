package com.albert.godworld.arm.domain.notify;

public enum NotifyType {

    ADMIN(1,"admin"),
    READER(2,"reader"),
    SOCIAL(3,"social"),
    BOOK(4,"book"),
    POINT(5,"point"),

    ;
    private final int code;
    private final String description;

    NotifyType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
