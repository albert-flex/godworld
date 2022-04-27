package com.albert.godworld.arm.controller.user;

import com.albert.godworld.arm.controller.CurdController;
import com.albert.godworld.arm.domain.user.Permission;
import com.albert.godworld.arm.service.user.GroupPermissionService;
import com.albert.godworld.arm.service.user.PermissionService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/permission")
public class PermissionController extends CurdController<Permission> {

    private final PermissionService permissionService;
    private final GroupPermissionService groupPermissionService;

    @Autowired
    public PermissionController(PermissionService permissionService,
                                GroupPermissionService groupPermissionService) {
        this.permissionService = permissionService;
        this.groupPermissionService = groupPermissionService;
    }

    @Override
    protected IService<Permission> service() {
        return permissionService;
    }

    @GetMapping("/one/{id}")
    public Permission getOne(@PathVariable("name") String name){
        return permissionService.getOne(name);
    }

    @GetMapping("/list/{groupId}")
    public List<Permission> pageOf(@PathVariable("groupId") Long groupId){
        return groupPermissionService.listOf(groupId);
    }

    @PostMapping
    public Permission add(@RequestBody Permission permission){
        permissionService.save(permission);
        return permission;
    }

    @PostMapping("/add/{groupId}/{permissionId}")
    public Boolean addPermission(@PathVariable("groupId") Long groupId,
                                 @PathVariable("permissionId") Long permissionId){
        return groupPermissionService.addPermission(groupId,permissionId);
    }

    @PostMapping("/remove/{groupId}/{permissionId}")
    public Boolean removePermission(@PathVariable("groupId") Long groupId,
                                 @PathVariable("permissionId") Long permissionId){
        return groupPermissionService.removePermission(groupId,permissionId);
    }
}
