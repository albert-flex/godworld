package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.domain.book.BookVolume;
import com.albert.godworld.arm.mapper.book.BookVolumeMapper;
import com.albert.godworld.arm.service.author.AuthorRecordService;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.albert.godworld.arm.service.book.BookPartService;
import com.albert.godworld.arm.service.book.BookVolumeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookVolumeServiceSPI extends ServiceImpl<BookVolumeMapper, BookVolume>
    implements BookVolumeService {

    private final BookInfoService bookInfoService;
    private final BookPartService bookPartService;
    private final AuthorRecordService authorRecordService;

    @Autowired
    public BookVolumeServiceSPI(BookInfoService bookInfoService, BookPartService bookPartService, AuthorRecordService authorRecordService) {
        this.bookInfoService = bookInfoService;
        this.bookPartService = bookPartService;
        this.authorRecordService = authorRecordService;
    }

    @Override
    public BookVolume publish(BookVolume volume) {
        BookInfo bookInfo = bookInfoService.getById(volume.getBookId());
        if (bookInfo == null) return volume;

        boolean result = super.save(volume);
        if (!result) return volume;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.PUBLISH_VOLUME, volume.getId());
        return volume;
    }

    @Override
    public boolean edit(BookVolume volume) {
        BookInfo bookInfo = bookInfoService.getById(volume.getBookId());
        if (bookInfo == null) return false;

        boolean result = super.updateById(volume);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.EDIT_VOLUME, volume.getId());
        return true;
    }

    @Override
    public boolean removeVolume(Long id) {
        BookVolume volume = super.getById(id);
        BookInfo bookInfo = bookInfoService.getById(volume.getBookId());
        if (bookInfo == null) return false;

        boolean result = bookPartService.removeById(id);
        if (!result) return false;

        authorRecordService.putRecord(bookInfo.getAuthorId(), AuthorRecordType.REMOVE_PART, id);
        return true;
    }

    @Override
    public Page<BookVolume> pageOf(Page<BookVolume> page, Long partId) {
        QueryWrapper<BookVolume> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("part_id",page);
        return super.page(page,queryWrapper);
    }
}
