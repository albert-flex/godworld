package com.albert.godworld.arm.resource.service.other.impl;

import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.INTERFACES)
public class RequestCallImpl implements RequestCall {

    private RVError er;

    @Override
    public RVError error() {
        return er;
    }

    @Override
    public void setError(RVError error) {
        this.er=error;
    }
}
