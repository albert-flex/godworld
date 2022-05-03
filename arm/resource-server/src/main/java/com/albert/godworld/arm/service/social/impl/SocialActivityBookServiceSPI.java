package com.albert.godworld.arm.service.social.impl;

import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.domain.social.SocialActivity;
import com.albert.godworld.arm.domain.social.SocialActivityBook;
import com.albert.godworld.arm.mapper.social.SocialActivityBookMapper;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.albert.godworld.arm.service.social.SocialActivityBookService;
import com.albert.godworld.arm.service.social.SocialActivityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SocialActivityBookServiceSPI extends ServiceImpl<SocialActivityBookMapper, SocialActivityBook>
        implements SocialActivityBookService {

    private final BookInfoService bookInfoService;

    private final SocialActivityService socialActivityService;

    @Autowired
    public SocialActivityBookServiceSPI(BookInfoService bookInfoService, SocialActivityService socialActivityService) {
        this.bookInfoService = bookInfoService;
        this.socialActivityService = socialActivityService;
    }

    @Override
    public Page<BookInfo> bookOf(Page<BookInfo> page, Long activityId) {
        Page<SocialActivityBook> page1 = new Page<>();
        page1.setCurrent(page.getCurrent());
        page1.setSize(page.getSize());
        page1.setOrders(page.orders());

        LambdaQueryWrapper<SocialActivityBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialActivityBook::getActivityId, activityId);
        page1 = super.page(page1, queryWrapper);
        List<Long> ids = new LinkedList<>();
        for (int i = 0; i != page1.getRecords().size(); ++i) {
            ids.add(page1.getRecords().get(i).getBookId());
        }

        List<BookInfo> result = bookInfoService.listByIds(ids);
        page.setPages(page1.getPages());
        page.setTotal(page1.getTotal());
        page.setRecords(result);
        return page;
    }

    private boolean checkBook(Long bookId,Long activityId){
        LambdaQueryWrapper<SocialActivityBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialActivityBook::getActivityId, activityId);
        queryWrapper.eq(SocialActivityBook::getBookId, bookId);
        SocialActivityBook book = super.getOne(queryWrapper);
        return book!=null;
    }

    @Override
    public boolean addBook(Long bookId, Long activityId) {
        if(checkBook(bookId,activityId))return false;
        SocialActivity activity=socialActivityService.getById(activityId);
        if(activity==null||!activity.getActOn())return false;

        SocialActivityBook insert = new SocialActivityBook();
        insert.setBookId(bookId);
        insert.setActivityId(activityId);
        insert.setSocialId(activity.getSocialId());

        return super.save(insert);
    }

    @Override
    public boolean removeBook(Long bookId, Long activityId) {
        if(!checkBook(bookId,activityId))return false;
        SocialActivity activity=socialActivityService.getById(activityId);
        if(activity==null||!activity.getActOn())return false;

        LambdaQueryWrapper<SocialActivityBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialActivityBook::getActivityId,activityId);
        queryWrapper.eq(SocialActivityBook::getBookId,bookId);
        return super.remove(queryWrapper);
    }
}
