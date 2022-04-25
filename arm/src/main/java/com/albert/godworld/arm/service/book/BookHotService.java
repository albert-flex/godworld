package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookHot;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

public interface BookHotService extends IService<BookHot> {

    //update DayHot
    boolean updateDayHot();

    //find DayHot
    Page<BookHot> findDayHot(Page<BookHot> page, Date day);
}
