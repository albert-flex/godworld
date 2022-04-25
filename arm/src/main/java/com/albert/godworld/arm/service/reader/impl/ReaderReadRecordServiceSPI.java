package com.albert.godworld.arm.service.reader.impl;

import com.albert.godworld.arm.domain.reader.ReaderReadRecord;
import com.albert.godworld.arm.mapper.reader.ReaderReadRecordMapper;
import com.albert.godworld.arm.service.reader.ReaderReadRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReaderReadRecordServiceSPI extends ServiceImpl<ReaderReadRecordMapper, ReaderReadRecord>
    implements ReaderReadRecordService {

    @Override
    public void markReadBook(Long readerId, Long bookId, Long chapterId) {
        ReaderReadRecord record=new ReaderReadRecord();
        record.setBookId(bookId);
        record.setReaderId(readerId);
        record.setChapterId(chapterId);
        record.setTime(new Date());
        super.save(record);
    }

    @Override
    public Page<ReaderReadRecord> pageOf(Page<ReaderReadRecord> page, Long readerId) {
        QueryWrapper<ReaderReadRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("readerId",readerId);
        return super.page(page,queryWrapper);
    }
}
