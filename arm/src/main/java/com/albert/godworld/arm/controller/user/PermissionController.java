package com.albert.godworld.arm.controller.user;

import com.albert.godworld.arm.domain.user.Permission;
import com.albert.godworld.arm.domain.user.User;
import com.albert.godworld.arm.service.user.PermissionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public Permission create(@RequestBody Permission permission){
        permissionService.save(permission);
        return permission;
    }

    @GetMapping("/id/{id}")
    public Permission getById(@PathVariable("id") Long id){
        return permissionService.getById(id);
    }

    @GetMapping("/list/{groupId}")
    public List<Permission> listByUser(@PathVariable("groupId") Long userId){
        return permissionService.list(userId);
    }

    @PutMapping("/add/{groupId}/{permId}")
    public Boolean addToUser(@PathVariable("groupId") Long groupId,@PathVariable("permId") Long permId){
        return permissionService.addToGroup(groupId,permId);
    }

    @GetMapping("/page")
    public Page<Permission> pageOfAll(Page<Permission> page){
        return permissionService.page(page);
    }

    @PutMapping("/remove/{groupId}/{permId}")
    public Boolean removeFromUser(@PathVariable("groupId") Long groupId,@PathVariable("groupId") Long permId){
        return permissionService.removeFromGroup(groupId,permId);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return permissionService.removeById(id);
    }
}
