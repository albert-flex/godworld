package com.albert.godworld.godarm.social.domain;

public class SocialRequest {
    public enum Request {
        IN, OUT, SOCIAL_TRANSFER, ADMIN
    }

    public enum State{
        UN_HANDLE, REFUSED, CONFIRMED,
    }

    private Long socialId;
    private Long hockId;
    private Request request;
    private State state;
    private String detail;
}
