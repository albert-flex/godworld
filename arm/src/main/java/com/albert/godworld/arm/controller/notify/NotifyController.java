package com.albert.godworld.arm.controller.notify;

import com.albert.godworld.arm.controller.CurdController;
import com.albert.godworld.arm.domain.notify.NotifyInfo;
import com.albert.godworld.arm.domain.notify.NotifyType;
import com.albert.godworld.arm.service.notify.NotifyInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notify")
public class NotifyController extends CurdController<NotifyInfo> {

    private final NotifyInfoService notifyInfoService;

    @Autowired
    public NotifyController(NotifyInfoService notifyInfoService) {
        this.notifyInfoService = notifyInfoService;
    }

    @Override
    protected IService<NotifyInfo> service() {
        return notifyInfoService;
    }
    
    @GetMapping("/page/{type}/{sourceId}")
    public Page<NotifyInfo> pageOf(Page<NotifyInfo> page,
                                   String type, Long sourceId) {
        return notifyInfoService.pageOf(page, NotifyType.valueOf(type),sourceId);
    }
}
