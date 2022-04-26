package com.albert.godworld.arm.service.point;

import com.albert.godworld.arm.domain.point.PointScore;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PointScoreService extends IService<PointScore> {

    PointScore scoreOf(Long userId);

    boolean checkBalance(Long userId,Long outCome);
    boolean recharge(Long userId,Long outCome);

    boolean consume(Long userId,Long outCome);
}
