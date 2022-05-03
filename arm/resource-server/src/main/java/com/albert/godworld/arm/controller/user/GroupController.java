package com.albert.godworld.arm.controller.user;

import com.albert.godworld.arm.domain.user.UGroup;
import com.albert.godworld.arm.domain.user.User;
import com.albert.godworld.arm.service.user.UGroupService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final UGroupService uGroupService;

    @Autowired
    public GroupController(UGroupService uGroupService) {
        this.uGroupService = uGroupService;
    }

    @GetMapping("/id/{id}")
    public UGroup getById(@PathVariable("id") Long id){
        return uGroupService.getById(id);
    }

    @GetMapping("/list/{userId}")
    public List<UGroup> listByUser(@PathVariable("userId") Long userId){
        return uGroupService.list(userId);
    }

    @PostMapping
    public UGroup create(@RequestBody UGroup group){
        uGroupService.save(group);
        return group;
    }

    @PutMapping
    public Boolean update(@RequestBody UGroup group){
        return group.getId()!=null && uGroupService.updateById(group);
    }

    @PutMapping("/add/{groupId}/{userId}")
    public Boolean addToUser(@PathVariable("userId") Long userId,@PathVariable("groupId") Long groupId){
        return uGroupService.addToUser(userId,groupId);
    }

    @PutMapping("/remove/{groupId}/{userId}")
    public Boolean removeFromUser(@PathVariable("userId") Long userId,@PathVariable("groupId") Long groupId){
        return uGroupService.removeFromUser(userId,groupId);
    }

    @GetMapping("/page")
    public Page<UGroup> pageOfAll(Page<UGroup> page){
        return uGroupService.page(page);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return uGroupService.removeById(id);
    }
}
