package com.albert.godworld.arm.resource.controller.author;


import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import sun.security.util.SecurityConstants;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    private final PrincipalConvert principalConvert;

    @Autowired
    public AuthorController(AuthorService authorService, PrincipalConvert principalConvert) {
        this.authorService = authorService;
        this.principalConvert = principalConvert;
    }

    @GetMapping("/now")
    public Object now(Principal principal){
        return principalConvert.convert(principal);
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
    @PreAuthorize("hasAuthority('ViewBook')")
    public AuthorInfo getById(@PathVariable("id") Long id){
        return authorService.getById(id);
    }

    @DeleteMapping
    public Boolean remove(@PathVariable("id") Long id){
        return authorService.removeById(id);
    }
}
