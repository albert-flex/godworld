package com.albert.godworld.arm.service.notify;

import com.albert.godworld.arm.domain.notify.NotifyInfo;
import com.albert.godworld.arm.domain.notify.NotifyType;
import com.albert.godworld.arm.mapper.notify.NotifyInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NotifyInfoServiceImpl extends ServiceImpl<NotifyInfoMapper, NotifyInfo>
    implements NotifyInfoService{

    @Override
    public void notify(NotifyInfo notifyInfo) {
        super.save(notifyInfo);
    }

    @Override
    public boolean consume(Long notifyId) {
        UpdateWrapper<NotifyInfo> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",notifyId);
        updateWrapper.set("read",true);
        return super.update(updateWrapper);
    }

    @Override
    public Page<NotifyInfo> pageOf(Page<NotifyInfo> page, NotifyType sourceType, Long sourceId) {
        QueryWrapper<NotifyInfo> queryWrapper=new QueryWrapper<>();
        if(sourceType!=null){
            queryWrapper.eq("source",sourceType);
        }
        queryWrapper.eq("source_id",sourceId);
        return super.page(page,queryWrapper);
    }
}
