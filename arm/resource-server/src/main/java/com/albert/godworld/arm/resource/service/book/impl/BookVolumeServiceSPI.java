package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookVolume;
import com.albert.godworld.arm.resource.mapper.book.BookVolumeMapper;
import com.albert.godworld.arm.resource.service.book.BookVolumeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookVolumeServiceSPI extends ServiceImpl<BookVolumeMapper, BookVolume>
    implements BookVolumeService {

    @Override
    public List<BookVolume> volumeOf(Long bookId) {
        LambdaQueryWrapper<BookVolume> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookVolume::getBookId,bookId);
        return super.list(queryWrapper);
    }
}
