package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.mapper.book.BookChapterMapper;
import com.albert.godworld.arm.resource.service.book.BookChapterService;
import com.albert.godworld.arm.resource.vo.book.BookChapterInfoVo;
import com.albert.godworld.arm.resource.vo.book.BookChapterVo;
import com.albert.godworld.arm.resource.vo.book.BookVolumeVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookChapterServiceSPI extends ServiceImpl<BookChapterMapper, BookChapter>
    implements BookChapterService {

    @Override
    public Page<BookChapter> chapterOfVolume(Page<BookChapter> page, Long volumeId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getVolumeId, volumeId);
        return super.page(page,queryWrapper);
    }

    @Override
    public long countChapter(Long bookId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getBookId,bookId);
        return super.count(queryWrapper);
    }

    @Override
    public BookChapter newestChapter(Long bookId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getBookId,bookId);
        queryWrapper.orderByDesc(BookChapter::getCreateTime).last("limit 1");
        return super.getOne(queryWrapper);
    }

    @Override
    public List<BookVolumeVo> volumeOfBook(Long bookId) {
        List<BookChapterVo> vos=super.baseMapper.listChaptersOfBook(bookId);
        return BookVolumeVo.parse(vos);
    }

    @Override
    public BookChapterInfoVo getChapter(Long chapterId) {
        return super.baseMapper.getInfoOnChapter(chapterId);
    }
}
