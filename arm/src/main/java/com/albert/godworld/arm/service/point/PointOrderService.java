package com.albert.godworld.arm.service.point;

import com.albert.godworld.arm.domain.point.PointOrder;
import com.albert.godworld.arm.domain.point.PointOrderType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PointOrderService extends IService<PointOrder> {

    Page<PointOrder> pageOf(Page<PointOrder> page, Long sourceId);

    void order(PointOrderType type, Long sourceId, Long targetId, Long outCome, String back);

    boolean confirm(Long orderId);

    boolean cancel(Long orderId);
}
