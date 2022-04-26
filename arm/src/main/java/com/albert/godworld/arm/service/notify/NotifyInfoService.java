package com.albert.godworld.arm.service.notify;

import com.albert.godworld.arm.domain.notify.NotifyInfo;
import com.albert.godworld.arm.domain.notify.NotifyType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface NotifyInfoService extends IService<NotifyInfo> {

    void notify(NotifyInfo notifyInfo);
    boolean consume(Long notifyId);

    Page<NotifyInfo> pageOf(Page<NotifyInfo> page, NotifyType sourceType,Long sourceId);
}
