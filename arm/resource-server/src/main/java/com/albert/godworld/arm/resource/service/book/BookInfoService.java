package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.vo.book.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookInfoService extends IService<BookInfo> {

    Page<BookVo> pageOfAuthor(Page<BookVo> page,String authorName);
    Page<BookVo> pageOfAuthorId(Page<BookVo> page,Long authorId);

    Page<BookVo> pageOfUpdate(Page<BookVo> page);
    List<BookVo> OfPoint();
    List<BookVo> OfPointBoard(String board);
    Page<BookVo> queryByName(Page<BookVo> page,String bookName);
    Page<BookVo> queryByBoard(Page<BookVo> page,String board);
    Page<BookVo> queryByTags(Page<BookVo> page, List<String> tag);
    BookVo getById(Long id);
    Page<BookVo> query(Page<BookVo> page, Integer year, Integer month, List<String> tags, String board);
}
