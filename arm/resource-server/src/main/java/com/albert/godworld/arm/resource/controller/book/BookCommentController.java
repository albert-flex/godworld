package com.albert.godworld.arm.resource.controller.book;

import com.albert.godworld.arm.resource.domain.book.BookComment;
import com.albert.godworld.arm.resource.domain.user.Permissions;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.book.BookCommentService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.book.BookCommentVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/book_comment")
@AllArgsConstructor
public class BookCommentController {

    private final BookCommentService bookCommentService;
    private final PrincipalConvert convert;

    @GetMapping("/page/book/{bookId}")
    public Page<BookCommentVo> pageOfBook(@PathVariable("bookId") Long bookId, Page<BookCommentVo> page){
        return bookCommentService.pageOf(page,bookId);
    }

    @GetMapping("/page/user/{userId}")
    public Page<BookCommentVo> pageOfReader(@PathVariable("userId") Long userId,Page<BookCommentVo> page){
        return bookCommentService.pageOfReader(page,userId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_PER','USER_PER')")
    public RV<BookComment> create(@RequestBody BookComment bookComment, Principal principal){
        User user=convert.convert(principal);
        //非管理员操作，必须为用户本身操作数据
        if(!Permissions.ADMIN_PER.hasIn(user)){
            if(!user.getId().equals(bookComment.getUserId())){
                return RVError.USER_NOT_SAME.to();
            }
        }
        bookCommentService.save(bookComment);
        return RV.success(bookComment);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id")Long id){
        return bookCommentService.removeById(id);
    }
}
