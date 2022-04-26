package com.albert.godworld.arm.service.point.impl;

import com.albert.godworld.arm.domain.point.PointOrder;
import com.albert.godworld.arm.domain.point.PointOrderState;
import com.albert.godworld.arm.domain.point.PointOrderType;
import com.albert.godworld.arm.mapper.point.PointOrderMapper;
import com.albert.godworld.arm.service.point.PointOrderService;
import com.albert.godworld.arm.service.point.PointRecordService;
import com.albert.godworld.arm.service.point.PointScoreService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PointOrderServiceSPI extends ServiceImpl<PointOrderMapper, PointOrder>
    implements PointOrderService {

    private final PointScoreService pointScoreService;
    private final PointRecordService pointRecordService;

    @Autowired
    public PointOrderServiceSPI(PointScoreService pointScoreService, PointRecordService pointRecordService) {
        this.pointScoreService = pointScoreService;
        this.pointRecordService = pointRecordService;
    }

    @Override
    public Page<PointOrder> pageOf(Page<PointOrder> page, Long sourceId) {
        QueryWrapper<PointOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("source_id",sourceId);
        return super.page(page,queryWrapper);
    }

    @Override
    public void order(PointOrderType type, Long sourceId, Long targetId, Long outCome, String back) {
        PointOrder pointOrder=new PointOrder();
        pointOrder.setType(type);
        pointOrder.setOrderScore(outCome);
        pointOrder.setCreateTime(new Date());
        pointOrder.setState(PointOrderState.OPENED);
        pointOrder.setSourceId(sourceId);
        pointOrder.setTargetId(targetId);
        pointOrder.setBack(back);
        super.save(pointOrder);
    }

    @Override
    public boolean confirm(Long orderId) {
        PointOrder order=super.getById(orderId);
        if(order==null||order.getState()!=PointOrderState.OPENED)return false;

        Long outCome=order.getOrderScore();
        if(pointScoreService.checkBalance(order.getSourceId(),outCome)){
            if(pointScoreService.consume(order.getSourceId(),outCome)){
                pointRecordService.putRecord(order);
                return true;
            } else {
                order.setState(PointOrderState.FAILED);
                order.setFailedWord("consume failed.");
                super.updateById(order);
            }
        }else {
            order.setState(PointOrderState.FAILED);
            order.setFailedWord("balance not enough.");
            super.updateById(order);
        }
        return false;
    }

    @Override
    public boolean cancel(Long orderId) {
        PointOrder order=super.getById(orderId);
        if(order==null||order.getState()!=PointOrderState.OPENED)return false;

        order.setState(PointOrderState.CANCELED);
        return super.updateById(order);
    }
}
