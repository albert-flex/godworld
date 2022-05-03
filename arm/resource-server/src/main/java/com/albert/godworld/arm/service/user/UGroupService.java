package com.albert.godworld.arm.service.user;

import com.albert.godworld.arm.domain.user.UGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UGroupService extends IService<UGroup> {

    List<UGroup> list(Long userId);

    boolean addToUser(Long userId, Long groupId);

    boolean removeFromUser(Long userId, Long groupId);
}

