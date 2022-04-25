package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookHot;
import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.mapper.book.BookHotMapper;
import com.albert.godworld.arm.mapper.book.BookInfoMapper;
import com.albert.godworld.arm.service.book.BookHotService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookHotServiceSPI extends ServiceImpl<BookHotMapper, BookHot>
        implements BookHotService {

    private final BookInfoMapper bookInfoMapper;

    @Autowired
    public BookHotServiceSPI(BookInfoMapper bookInfoMapper) {
        this.bookInfoMapper = bookInfoMapper;
    }

    /**
     * 根据点击量排序
     *
     * @return
     */
    @Override
    public boolean updateDayHot() {
        QueryWrapper<BookInfo> query = new QueryWrapper<>();
        query.orderByDesc("click_count");
        Page<BookInfo> page = new Page<>();
        page.setCurrent(1);
        page.setSize(20);
        page = bookInfoMapper.selectPage(page, query);

        //Get Now Day
        Date date=new Date();
        //......

        List<BookHot> hotList=new LinkedList<>();
        //write page to day hot!
        for(int i=0;i<=page.getRecords().size();++i){
            BookInfo info=page.getRecords().get(i);
            BookHot hot=new BookHot();
            hot.setHotDay(date);
            hot.setBookId(info.getId());
            hot.setAuthorId(info.getAuthorId());
            hot.setOrder(i);
            hot.setHotScore(info.getClickCount());
            hotList.add(hot);
        }

        return super.saveBatch(hotList);
    }

    @Override
    public Page<BookHot> findDayHot(Page<BookHot> page, Date day) {
        QueryWrapper<BookHot> hot=new QueryWrapper<>();
        hot.eq("hot_day",day);
        return super.page(page,hot);
    }
}
