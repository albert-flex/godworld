package com.albert.godworld.arm.controller.author;


import com.albert.godworld.arm.domain.author.AuthorInfo;
import com.albert.godworld.arm.service.author.AuthorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorInfo create(@RequestBody AuthorInfo info){
        authorService.save(info);
        return info;
    }

    @PutMapping
    public Boolean update(@RequestBody AuthorInfo info){
        return authorService.updateById(info);
    }

    @GetMapping("/page")
    public Page<AuthorInfo> page(Page<AuthorInfo> page){
        return authorService.page(page);
    }

    @GetMapping("/query/name/{name}")
    public Page<AuthorInfo> queryName(@PathVariable("name") String name,Page<AuthorInfo> page){
        return authorService.queryByName(page,name);
    }

    @GetMapping("/id/{id}")
    public AuthorInfo getById(@PathVariable("id") Long id){
        return authorService.getById(id);
    }

    @DeleteMapping
    public Boolean remove(@PathVariable("id") Long id){
        return authorService.removeById(id);
    }
}
