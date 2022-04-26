package com.albert.godworld.arm.service.point.impl;

import com.albert.godworld.arm.domain.point.PointOrder;
import com.albert.godworld.arm.domain.point.PointRecord;
import com.albert.godworld.arm.mapper.point.PointRecordMapper;
import com.albert.godworld.arm.service.point.PointRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PointRecordServiceSPI extends ServiceImpl<PointRecordMapper, PointRecord>
        implements PointRecordService {

    @Override
    public Page<PointRecord> pageOf(Page<PointRecord> page, Long userId) {
        QueryWrapper<PointRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return super.page(page, queryWrapper);
    }

    @Override
    public void putRecord(PointOrder order) {
        PointRecord record=new PointRecord();
        record.setSourceId(order.getSourceId());
        record.setTargetId(order.getTargetId());
        record.setDetail(order.getBack());
        record.setTime(order.getHandleTime());
        record.setType(order.getType());
        record.setOrderId(order.getId());
        record.setOutCome(order.getOrderScore());
        super.save(record);
    }
}
