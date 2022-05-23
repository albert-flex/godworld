package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.book.BookVolume;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.service.book.BookVolumeService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.book.BookVolumeVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/book_volume")
@AllArgsConstructor
public class BookVolumeController {


    private final BookVolumeService bookVolumeService;
    private final PrincipalConvert convert;
    private final AuthorService authorService;
    private final BookInfoService bookInfoService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('AUTHOR_PER')")
    public RV<Boolean> create(@RequestParam("bookId") Long bookId, @RequestParam("name") String name, @RequestParam
            (value = "prevVolumeId",defaultValue = "0") Long prevVolumeId, Principal principal) {
        User user = convert.convert(principal);
        Long authorId = authorService.getAuthorIdByUserId(user.getId());
        BookInfo bookInfo = bookInfoService.getById(bookId);
        if (!authorId.equals(bookInfo.getAuthorId())) return RVError.AUTHOR_USER_NOT_SAME.to();

        return RV.success(bookVolumeService.create(bookId,name,prevVolumeId));
    }

    @GetMapping("/of/{bookId}")
    public List<BookVolume> volumeList(@PathVariable("bookId") Long bookId){
        LambdaQueryWrapper<BookVolume> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookVolume::getBookId,bookId);
        return bookVolumeService.list(queryWrapper);
    }

    @PutMapping("/modify")
    public RV<Boolean> modify(@RequestParam("volumeId") Long volumeId,@RequestParam("pre") Long pre,@RequestParam("name") String name){
        return RV.success(bookVolumeService.change(volumeId,name,pre));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('AUTHOR_PER')")
    public RV<Boolean> remove(@PathVariable("id") Long id,Principal principal){
        User user = convert.convert(principal);
        Long authorId = authorService.getAuthorIdByUserId(user.getId());
        BookVolume volume=bookVolumeService.getById(id);
        if(volume==null) return RVError.BOOK_VOLUME_NOT_FOUND.to();
        BookInfo bookInfo = bookInfoService.getById(volume.getBookId());
        if (!authorId.equals(bookInfo.getAuthorId())) return RVError.AUTHOR_USER_NOT_SAME.to();
        return RV.success(bookVolumeService.removeById(id));
    }
}
