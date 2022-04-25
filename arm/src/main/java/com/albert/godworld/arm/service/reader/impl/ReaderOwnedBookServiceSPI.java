package com.albert.godworld.arm.service.reader.impl;

import com.albert.godworld.arm.domain.reader.ReaderOwnedBook;
import com.albert.godworld.arm.mapper.reader.ReaderOwnedBookMapper;
import com.albert.godworld.arm.service.reader.ReaderOwnedBookService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ReaderOwnedBookServiceSPI extends ServiceImpl<ReaderOwnedBookMapper, ReaderOwnedBook>
    implements ReaderOwnedBookService {

    @Override
    public Page<ReaderOwnedBook> pageOf(Page<ReaderOwnedBook> page, Long readerId) {
        QueryWrapper<ReaderOwnedBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("reader_id",readerId);
        return super.page(page,queryWrapper);
    }
}
