package com.albert.godworld.arm.resource.controller.book;


import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.user.Permissions;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.BookDTO;
import com.albert.godworld.arm.resource.dto.BookQueryDTO;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.service.book.BookRankService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.book.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookInfoController {

    private final BookInfoService bookInfoService;
    private final AuthorService authorService;
    private final BookRankService bookRankService;
    private final PrincipalConvert convert;

    @PostMapping
    @PreAuthorize("hasAuthority('AUTHOR_PER')")
    public RV<BookDTO> create(@RequestBody BookDTO book, Principal principal) {
        User user = convert.convert(principal);
        if(!Permissions.ADMIN_PER.hasIn(user)){
            Long authorId = authorService.getAuthorIdByUserId(user.getId());
            if (authorId == null || !authorId.equals(book.getAuthorId())) {
                return RVError.AUTHOR_USER_NOT_SAME.to();
            }
        }

        if(!bookInfoService.create(book))return RVError.DATABASE_ERROR.to();
        return RV.success(book);
    }

    @PutMapping("/modify")
    @PreAuthorize("hasAuthority('AUTHOR_PER')")
    public RV<Boolean> updateBook(@RequestBody BookDTO book, Principal principal){
        User user = convert.convert(principal);
        if(!Permissions.ADMIN_PER.hasIn(user)){
            Long authorId = authorService.getAuthorIdByUserId(user.getId());
            if (authorId == null || !authorId.equals(book.getAuthorId())) {
                return RVError.AUTHOR_USER_NOT_SAME.to();
            }
        }

        boolean result=bookInfoService.modify(book);
        if(!result){
            return RVError.BOOK_MODIFY_FAIL.to();
        }else {
            return RV.success(true);
        }
    }

    @PutMapping
    public Boolean update(@RequestBody BookInfo bookInfo) {
        return bookInfoService.updateById(bookInfo);
    }

    @GetMapping("/rank")
    public List<BookVo> rank() {
        return bookRankService.getRankList();
    }

    @GetMapping("/rank/all")
    public Map<String, List<BookVo>> rankAllBoards() {
        return bookRankService.getAllRankListOfBoard();
    }

    @GetMapping("/page/author/{authorName}")
    public Page<BookVo> pageOfAuthor(@PathVariable("authorName") String authorName, Page<BookVo> page) {
        return bookInfoService.pageOfAuthor(page, authorName);
    }

    @GetMapping("/page/author/id/{authorId}")
    public Page<BookVo> pageOfAuthorId(@PathVariable("authorId") Long authorId, Page<BookVo> page) {
        return bookInfoService.pageOfAuthorId(page, authorId);
    }


    @GetMapping("/page/board/{board}")
    public Page<BookVo> pageOfBoard(@PathVariable("board") String board, Page<BookVo> page) {
        return bookInfoService.queryByBoard(page, board);
    }

    @GetMapping("/page/tags")
    public Page<BookVo> pageOfTags(@RequestParam("tags") String[] tags, Page<BookVo> page) {
        return bookInfoService.queryByTags(page, Arrays.asList(tags));
    }

    @GetMapping("/get/id/{id}")
    public BookVo getById(@PathVariable("id") Long id) {
        return bookInfoService.getOnId(id);
    }

    @GetMapping("/page/update")
    public Page<BookVo> getUpdate(Page<BookVo> page) {
        return bookInfoService.pageOfUpdate(page);
    }

    @PostMapping("/update/rank")
    public void updateRank() {
        bookRankService.updateRankList();
        bookRankService.updateAllRankListOfBoard();
    }

    @GetMapping("/page/name/{name}")
    public Page<BookVo> queryByName(@PathVariable("name") String bookName, Page<BookVo> page) {
        return bookInfoService.queryByName(page, bookName);
    }

    @PutMapping("/page/query")
    public Page<BookVo> query(@RequestBody BookQueryDTO queryDTO, Page<BookVo> page) {
        return bookInfoService.query(page, queryDTO.getYear(), queryDTO.getMonth(), queryDTO.getTags(), queryDTO.getBoard());
    }

    @PutMapping("/set_present/{authorId}/{bookId}")
    public Boolean setPresentBook(@PathVariable("authorId") Long authorId, @PathVariable("bookId") Long bookId) {
        return bookInfoService.setPresentBook(authorId, bookId);
    }

    @PutMapping("/erase_present/{authorId}")
    public Boolean erasePresentBook(@PathVariable("authorId") Long authorId) {
        return bookInfoService.erasePresentBook(authorId);
    }


    @GetMapping("/page/all")
    public Page<BookInfo> all(Page<BookInfo> page) {
        return bookInfoService.page(page);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        return bookInfoService.removeById(id);
    }
}
