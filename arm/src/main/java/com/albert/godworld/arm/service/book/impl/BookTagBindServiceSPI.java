package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookTag;
import com.albert.godworld.arm.domain.book.BookTagBind;
import com.albert.godworld.arm.mapper.book.BookTagBindMapper;
import com.albert.godworld.arm.service.book.BookTagBindService;
import com.albert.godworld.arm.service.book.BookTagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookTagBindServiceSPI extends ServiceImpl<BookTagBindMapper, BookTagBind>
    implements BookTagBindService {

    private final BookTagService bookTagService;

    @Autowired
    public BookTagBindServiceSPI(BookTagService bookTagService) {
        this.bookTagService = bookTagService;
    }

    @Override
    public boolean bind(List<BookTag> tags, Long bookId) {
        //remove pre tag binds
        QueryWrapper<BookTagBind> query = new QueryWrapper<>();
        query.eq("book_id", bookId);
        super.remove(query);

        List<BookTagBind> binds = new LinkedList<>();
        for (BookTag tag : tags) {
            BookTagBind bind = new BookTagBind();
            bind.setBookId(bookId);
            bind.setTagId(tag.getId());
            binds.add(bind);
        }
        //add new tag binds
        return super.saveBatch(binds);
    }

    @Override
    public List<BookTag> tagBindsOf(Long bookId) {
        QueryWrapper<BookTagBind> bindsQuery=new QueryWrapper<>();
        bindsQuery.eq("book_id",bookId);

        Collection<BookTagBind> binds=super.list(bindsQuery);
        List<Long> ids=new LinkedList<>();
        for(BookTagBind b: binds){
            ids.add(b.getTagId());
        }

        QueryWrapper<BookTag> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",ids);
        return bookTagService.list(queryWrapper);
    }
}
