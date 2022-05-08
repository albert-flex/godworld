package com.albert.godworld.arm.resource.service.task;

import com.albert.godworld.arm.resource.service.book.BookRankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@Slf4j
public class BookRankTask {

    private final BookRankService bookRankService;

    @Scheduled(cron = "59 59 23 * * ? ")
    public void updateBookRank(){
        log.info("开始更新排行榜: "+new Date());
        bookRankService.updateRankList();
        bookRankService.updateAllRankListOfBoard();
        log.info("更新排行榜完成: "+new Date());
    }
}
