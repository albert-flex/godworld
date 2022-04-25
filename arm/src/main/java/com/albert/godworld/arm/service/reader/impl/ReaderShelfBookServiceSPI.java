package com.albert.godworld.arm.service.reader.impl;

import com.albert.godworld.arm.domain.reader.ReaderShelfBook;
import com.albert.godworld.arm.mapper.reader.ReaderShelfBookMapper;
import com.albert.godworld.arm.service.reader.ReaderShelfBookService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ReaderShelfBookServiceSPI extends ServiceImpl<ReaderShelfBookMapper, ReaderShelfBook>
        implements ReaderShelfBookService {

    @Override
    public Page<ReaderShelfBook> pageOf(Page<ReaderShelfBook> page, Long readerId) {
        QueryWrapper<ReaderShelfBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("reader_id",readerId);
        return super.page(page,queryWrapper);
    }
}
