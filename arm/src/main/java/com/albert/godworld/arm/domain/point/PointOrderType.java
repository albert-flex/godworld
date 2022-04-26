package com.albert.godworld.arm.domain.point;

public enum PointOrderType {

    ACTIVITY_GET(1,"get point from activity"),
    BUY(2,"out point to buy something"),
    SELL(3,"get point from sell something"),
    TRANSFER_OUT(4,"out point to others"),
    TRANSFER_IN(5,"get point from others"),

    ;


    private final int code;
    private final String description;

    PointOrderType(int code, String description) {
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
