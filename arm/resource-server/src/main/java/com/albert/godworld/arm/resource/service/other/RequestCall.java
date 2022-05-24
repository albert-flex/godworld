package com.albert.godworld.arm.resource.service.other;

import com.albert.godworld.arm.resource.dto.RVError;

public interface RequestCall {

    RVError error();
    void setError(RVError error);
}
