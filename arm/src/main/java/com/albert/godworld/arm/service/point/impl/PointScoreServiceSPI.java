package com.albert.godworld.arm.service.point.impl;

import com.albert.godworld.arm.domain.point.PointScore;
import com.albert.godworld.arm.mapper.point.PointScoreMapper;
import com.albert.godworld.arm.service.point.PointScoreService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PointScoreServiceSPI extends ServiceImpl<PointScoreMapper, PointScore>
        implements PointScoreService {

    @Override
    public PointScore scoreOf(Long userId) {
        QueryWrapper<PointScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean checkBalance(Long userId, Long outCome) {
        QueryWrapper<PointScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        PointScore score = super.getOne(queryWrapper);
        if (score == null) return false;

        return score.getPoint() >= outCome;
    }

    @Override
    public boolean recharge(Long userId, Long outCome) {
        if(outCome<0)return false;

        QueryWrapper<PointScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        PointScore score = super.getOne(queryWrapper);
        if (score == null) return false;

        PointScore pointScore=new PointScore();
        pointScore.setId(score.getId());
        pointScore.setPoint(pointScore.getPoint()+outCome);
        return super.updateById(pointScore);
    }

    @Override
    public boolean consume(Long userId, Long outCome) {
        if(outCome<0)return false;

        QueryWrapper<PointScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        PointScore score = super.getOne(queryWrapper);
        if (score == null) return false;

        if(score.getPoint()<outCome)return false;

        PointScore pointScore=new PointScore();
        pointScore.setId(score.getId());
        pointScore.setPoint(score.getPoint()-outCome);
        return super.updateById(pointScore);
    }
}
