package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.vo.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookInfoService extends IService<BookInfo> {

    Page<BookVo> pageOfAuthor(Page<BookVo> page,String authorName);
    List<BookVo> OfPoint();
    List<BookVo> OfPointBoard(String board);
    Page<BookVo> queryByName(Page<BookVo> page,String bookName);
    Page<BookVo> query(Page<BookVo> page, Integer year, Integer month, List<Long> tags, String board);
}
