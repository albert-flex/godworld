package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;

import java.util.List;
import java.util.Map;

public interface BookRankService {

    List<BookInfo> getRankList();
    Map<String,List<BookInfo>> getAllRankListOfBoard();

    void updateRankList();
    void updateAllRankListOfBoard();
}
