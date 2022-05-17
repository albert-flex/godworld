package com.albert.godworld.arm.resource.controller.author;


import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.user.Permissions;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final PrincipalConvert principalConvert;

    @GetMapping("/now")
    public Object now(Principal principal) {
        return principalConvert.convert(principal);
    }
    @PostMapping
    public AuthorInfo create(@RequestBody AuthorInfo info) {
        if (authorService.checkAuthorNameAvailable(info.getName())) {
            authorService.save(info);
        }
        return info;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_PER,AUTHOR_PER')")
    public RV<Boolean> update(@RequestBody AuthorInfo info, Principal principal) {
        User user = principalConvert.convert(principal);
        if(Permissions.ADMIN_PER.hasIn(user)){
            if (!user.getId().equals(info.getUserId())){
                return RVError.USER_NOT_SAME.to();
            }
            if (authorService.checkUserIdAndAuthorCorrect(info.getUserId(), info.getId())) {
                return RVError.AUTHOR_USER_NOT_SAME.to();
            }
        }
        boolean result = authorService.updateById(info);
        if (result) {
            return RV.success(true);
        } else {
            RVError.DATABASE_ERROR.to();
        }
        return RVError.UNKNOWN_ERROR.to();
    }

    @GetMapping("/page")
    public Page<AuthorInfo> page(Page<AuthorInfo> page) {
        return authorService.page(page);
    }

    @GetMapping("/by/ids")
    public List<AuthorInfo> findByIds(List<Long> ids){
        return authorService.listByIds(ids);
    }

    @GetMapping("/query/name/{name}")
    public Page<AuthorInfo> queryName(@PathVariable("name") String name, Page<AuthorInfo> page) {
        return authorService.queryByName(page, name);
    }

    @GetMapping("/id/{id}")
    public AuthorInfo getById(@PathVariable("id") Long id) {
        return authorService.getById(id);
    }

    @DeleteMapping
    public Boolean remove(@PathVariable("id") Long id) {
        return authorService.removeById(id);
    }
}
