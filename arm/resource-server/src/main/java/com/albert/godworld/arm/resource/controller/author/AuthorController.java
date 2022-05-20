package com.albert.godworld.arm.resource.controller.author;


import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.user.Permissions;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.author.AuthorNewestVo;
import com.albert.godworld.arm.resource.vo.author.AuthorUpdatedVo;
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

    @GetMapping("/page/update")
    public Page<AuthorUpdatedVo> updatedList(Page<AuthorUpdatedVo> page,@RequestParam(value = "day",defaultValue = "7") Integer day){
        return authorService.updateAuthors(page,day);
    }

    @GetMapping("/page/newest")
    public Page<AuthorNewestVo> upNewestList(Page<AuthorNewestVo> page,@RequestParam(value = "day",defaultValue = "7") Integer day){
        return authorService.newestAuthors(page,day);
    }

    @GetMapping("/page/name/{name}")
    public Page<AuthorUpdatedVo> updatePageByName(Page<AuthorUpdatedVo> page,@PathVariable("name") String name){
        return authorService.queryByName(page,name);
    }

    @GetMapping("/page/social/{name}")
    public Page<AuthorUpdatedVo> updatePageBySocial(Page<AuthorUpdatedVo> page,@PathVariable("name") String name){
        return authorService.queryBySocial(page,name);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_PER','AUTHOR_PER')")
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

    @GetMapping("/id/{id}")
    public AuthorInfo getById(@PathVariable("id") Long id) {
        return authorService.getById(id);
    }

    @DeleteMapping
    public Boolean remove(@PathVariable("id") Long id) {
        return authorService.removeById(id);
    }
}
