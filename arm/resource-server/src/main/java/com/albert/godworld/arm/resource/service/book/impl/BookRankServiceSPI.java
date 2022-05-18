package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookBoard;
import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.service.book.BookBoardService;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.service.book.BookRankService;
import com.albert.godworld.arm.resource.vo.BookVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookRankServiceSPI implements BookRankService {

    private final StringRedisTemplate redisTemplate;
    private final BookInfoService bookInfoService;
    private final BookBoardService bookBoardService;
    private final ObjectMapper objectMapper;

    private final String bookHotLib = "hot.book";
    private final String bookHotBoardLib = "hot.book.board-";

    @Override
    public List<BookVo> getRankList() {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(bookHotLib))) {
            return Collections.emptyList();
        }

        return hotOfBoard(bookHotLib);
    }

    @Override
    public Map<String, List<BookVo>> getAllRankListOfBoard() {
        List<BookBoard> list = bookBoardService.list();
        Map<String, List<BookVo>> map = new LinkedHashMap<>();
        for (BookBoard bookBoard : list) {
            String key = bookHotBoardLib + bookBoard.getId();
            List<BookVo> info_list = hotOfBoard(key);
            map.put(bookBoard.getName(), info_list);
        }
        return map;
    }

    private List<BookVo> hotOfBoard(String key) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
            return Collections.emptyList();
        }

        Long size = redisTemplate.opsForList().size(key);
        size = size == null ? 0L : size;

        List<String> list = redisTemplate.opsForList().range(key, 0, size);
        if (list == null || list.isEmpty()) return Collections.emptyList();

        List<BookVo> bookInfos = new LinkedList<>();
        try {
            for (int i = 0; i != list.size(); ++i) {
                String t = list.get(i);
                BookVo info = objectMapper.readValue(t, BookVo.class);
                bookInfos.add(info);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return bookInfos;
    }

    private void updateBoard(String key, List<BookVo> list) {
        redisTemplate.delete(key);
        try {
            for (BookVo bookInfo : list) {
                String s = objectMapper.writeValueAsString(bookInfo);
                redisTemplate.opsForList().leftPush(key, s);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateRankList() {
        List<BookVo> list=bookInfoService.OfPoint();
        updateBoard(bookHotLib,list);
    }

    @Override
    public void updateAllRankListOfBoard() {
        List<BookBoard> bookBoards=bookBoardService.list();
        for(int i=0;i!=bookBoards.size();++i){
            BookBoard bookBoard=bookBoards.get(i);
            List<BookVo> list=bookInfoService.OfPointBoard(bookBoard.getName());
            updateBoard(bookHotBoardLib+bookBoard.getId(),list);
        }
    }
}
