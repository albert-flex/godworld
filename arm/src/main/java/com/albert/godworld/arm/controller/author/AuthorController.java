package com.albert.godworld.arm.controller.author;

import com.albert.godworld.arm.controller.CurdController;
import com.albert.godworld.arm.domain.author.AuthorInfo;
import com.albert.godworld.arm.service.author.AuthorService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/author")
public class AuthorController extends CurdController<AuthorInfo> {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService=authorService;
    }

    @PostMapping("/bind/{userId}")
    public Boolean bind(@PathVariable("userId") Long userId,
                        @RequestBody AuthorInfo authorId){
        return authorService.bind(userId,authorId);
    }

    @PostMapping("/unbind/{authorId}")
    public Boolean unbind(@PathVariable("authorId") Long authorId){
        return authorService.unbind(authorId);
    }

    @GetMapping("/page/{name}")
    public Page<AuthorInfo> pageOf(Page<AuthorInfo> page,@PathVariable("name") String name){
        return authorService.pageOf(page,name);
    }

    @Override
    public IService<AuthorInfo> service() {
        return authorService;
    }
}
