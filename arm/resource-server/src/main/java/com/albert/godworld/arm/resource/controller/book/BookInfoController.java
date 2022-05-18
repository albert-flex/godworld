package com.albert.godworld.arm.resource.controller.book;


import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.dto.BookQueryDTO;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.service.book.BookRankService;
import com.albert.godworld.arm.resource.vo.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookInfoController {

    private final BookInfoService bookInfoService;
    private final BookRankService bookRankService;

    @Autowired
    public BookInfoController(BookInfoService bookInfoService, BookRankService bookRankService) {
        this.bookInfoService = bookInfoService;
        this.bookRankService = bookRankService;
    }

    @PostMapping
    public BookInfo create(@RequestBody BookInfo bookInfo){
        bookInfoService.save(bookInfo);
        return bookInfo;
    }

    @PutMapping
    public Boolean update(@RequestBody BookInfo bookInfo){
        return bookInfoService.updateById(bookInfo);
    }

    @GetMapping("/rank")
    public List<BookVo> rank(){
        return bookRankService.getRankList();
    }

    @GetMapping("/rank/all")
    public Map<String,List<BookVo>> rankAllBoards(){
        return bookRankService.getAllRankListOfBoard();
    }

    @GetMapping("/page/author/{authorName}")
    public Page<BookVo> pageOfAuthor(@PathVariable("authorName") String authorName,Page<BookVo> page){
        return bookInfoService.pageOfAuthor(page,authorName);
    }

    @PostMapping("/update/rank")
    public void updateRank(){
        bookRankService.updateRankList();
        bookRankService.updateAllRankListOfBoard();
    }
    @GetMapping("/page/name/{name}")
    public Page<BookVo> queryByName(@PathVariable("name") String bookName,Page<BookVo> page){
        return bookInfoService.queryByName(page,bookName);
    }

    public Page<BookVo> query(@RequestBody BookQueryDTO queryDTO,Page<BookVo> page){
        return bookInfoService.query(page,queryDTO.getYear(),queryDTO.getMonth(),queryDTO.getTags(),queryDTO.getBoard());
    }

    @GetMapping("/page/all")
    public Page<BookInfo> all(Page<BookInfo> page){
        return bookInfoService.page(page);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookInfoService.removeById(id);
    }
}
