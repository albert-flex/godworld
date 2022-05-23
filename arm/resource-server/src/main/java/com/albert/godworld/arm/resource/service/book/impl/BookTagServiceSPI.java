package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookTag;
import com.albert.godworld.arm.resource.domain.book.BookTagBind;
import com.albert.godworld.arm.resource.mapper.book.BookTagMapper;
import com.albert.godworld.arm.resource.service.book.BookTagBindService;
import com.albert.godworld.arm.resource.service.book.BookTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookTagServiceSPI extends ServiceImpl<BookTagMapper,BookTag> implements BookTagService {

    private final BookTagMapper bookTagMapper;
    private final BookTagBindService bookTagBindService;

    @Override
    @Transactional
    public void changeBookTag(Long bookId, BookTag[] tags) {
        LambdaQueryWrapper<BookTagBind> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookTagBind::getBookId,bookId);
        bookTagBindService.remove(queryWrapper);

        List<BookTagBind> binds=new ArrayList<>();
        for (BookTag tag : tags) {
            BookTagBind bind = new BookTagBind();
            bind.setBookId(bookId);
            bind.setTagId(tag.getId());
            binds.add(bind);
        }
        bookTagBindService.saveBatch(binds);
    }

    @Override
    public List<Long> tagIdListOf(List<String> names) {
        return super.baseMapper.tagIdListOf(names);
    }

    @Override
    public List<Long> idOfBookOnTagBinds(List<String> names, Long bookId) {
        return super.baseMapper.idOfBookOnTagBinds(names,bookId);
    }

    @Override
    public void reAttachTags(Long bookId, List<Long> tags) {
        LambdaQueryWrapper<BookTagBind> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookTagBind::getBookId,bookId);
        bookTagBindService.remove(queryWrapper);

        addTags(tags,bookId);
    }

    @Override
    public void addTags(List<Long> tags, Long bookId) {
        List<BookTagBind> binds=new ArrayList<>();
        for (Long tag : tags) {
            BookTagBind bind = new BookTagBind();
            bind.setBookId(bookId);
            bind.setTagId(tag);
            binds.add(bind);
        }
        bookTagBindService.saveBatch(binds);
    }
}
