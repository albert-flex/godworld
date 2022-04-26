package com.albert.godworld.arm.service.point;

import com.albert.godworld.arm.domain.point.PointOrder;
import com.albert.godworld.arm.domain.point.PointRecord;
import com.albert.godworld.arm.domain.point.PointOrderType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PointRecordService extends IService<PointRecord> {

    Page<PointRecord> pageOf(Page<PointRecord> page,Long userId);

    void putRecord(PointOrder order);
}
