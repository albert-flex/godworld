package com.albert.godworld.arm.service.reader.impl;

import com.albert.godworld.arm.domain.reader.ReaderInfo;
import com.albert.godworld.arm.mapper.reader.ReaderInfoMapper;
import com.albert.godworld.arm.service.reader.ReaderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ReaderInfoServiceSPI extends ServiceImpl<ReaderInfoMapper, ReaderInfo>
    implements ReaderInfoService {

    @Override
    public ReaderInfo findReaderByUserId(Long userId) {
        QueryWrapper<ReaderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean notWord(Long readerId) {
        ReaderInfo info=super.getById(readerId);
        if(info.getNotWord())return true;

        info.setNotWord(true);
        return super.updateById(info);
    }

    @Override
    public boolean isNotWord(Long readerId) {
        ReaderInfo info=super.getById(readerId);
        return info.getNotWord();
    }

    @Override
    public boolean doWord(Long readerId) {
        ReaderInfo info=super.getById(readerId);
        if(!info.getNotWord())return true;

        info.setNotWord(false);
        return super.updateById(info);
    }
}
