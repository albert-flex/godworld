package com.albert.godworld.arm.service.reader.impl;

import com.albert.godworld.arm.domain.reader.ReaderStarredBook;
import com.albert.godworld.arm.mapper.reader.ReaderStarredBookMapper;
import com.albert.godworld.arm.service.reader.ReaderStarredBookService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ReaderStarredBookServiceSPI extends ServiceImpl<ReaderStarredBookMapper, ReaderStarredBook>
    implements ReaderStarredBookService {

    @Override
    public Page<ReaderStarredBook> pageOf(Page<ReaderStarredBook> page, Long readerId) {
        QueryWrapper<ReaderStarredBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("reader_id",readerId);
        return super.page(page,queryWrapper);
    }
}
