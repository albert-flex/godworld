package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.vo.BookVo;

import java.util.List;
import java.util.Map;

public interface BookRankService {

    List<BookVo> getRankList();
    Map<String,List<BookVo>> getAllRankListOfBoard();

    void updateRankList();
    void updateAllRankListOfBoard();
}
