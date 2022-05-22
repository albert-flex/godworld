package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.ChapterDTO;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.book.BookChapterService;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.book.BookChapterInfoVo;
import com.albert.godworld.arm.resource.vo.book.BookVolumeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/book_chapter")
@AllArgsConstructor
public class BookChapterController {

    private final BookChapterService bookChapterService;
    private final PrincipalConvert convert;
    private final AuthorService authorService;
    private final BookInfoService bookInfoService;


    @PostMapping
    @PreAuthorize("hasAuthority('AUTHOR_PER')")
    public RV<Boolean> create(@RequestBody ChapterDTO chapterDTO, Principal principal){
        User user=convert.convert(principal);
        Long authorId=authorService.getAuthorIdByUserId(user.getId());
        BookInfo info=bookInfoService.getById(chapterDTO.getBookId());
        if(!info.getAuthorId().equals(authorId))return RVError.AUTHOR_USER_NOT_SAME.to();

        boolean result=bookChapterService.insertChapter(chapterDTO);
        if(!result){
            return RVError.DATABASE_ERROR.to();
        }else {
            return RV.success();
        }
    }

    @PutMapping
    public Boolean update(@RequestBody BookChapter bookChapter){
        return bookChapterService.updateById(bookChapter);
    }

    @GetMapping("/page/volume/{volumeId}")
    public Page<BookChapter> chapterOfVolume(@PathVariable("volumeId")Long volumeId,Page<BookChapter> page){
        return bookChapterService.chapterOfVolume(page,volumeId);
    }

    @GetMapping("/volumes/{bookId}")
    public List<BookVolumeVo> volumeVos(@PathVariable("bookId") Long bookId){
        return bookChapterService.volumeOfBook(bookId);
    }

    @GetMapping("/id/{id}")
    public BookChapterInfoVo getInfoByChapter(@PathVariable("id") Long id){
        return bookChapterService.getChapter(id);
    }


    @GetMapping("/newest/{bookId}")
    public BookChapter newestChapter(@PathVariable("bookId") Long bookId){
        return bookChapterService.newestChapter(bookId);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return bookChapterService.removeById(id);
    }
}
