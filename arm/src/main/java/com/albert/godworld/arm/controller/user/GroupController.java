package com.albert.godworld.arm.controller.user;


import com.albert.godworld.arm.controller.CurdController;
import com.albert.godworld.arm.domain.user.Group;
import com.albert.godworld.arm.service.user.GroupService;
import com.albert.godworld.arm.service.user.UserGroupService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController extends CurdController<Group> {

    private final GroupService groupService;
    private final UserGroupService userGroupService;

    @Autowired
    public GroupController(GroupService groupService,
                           UserGroupService userGroupService) {
        this.groupService = groupService;
        this.userGroupService = userGroupService;
    }

    @GetMapping("/one/{id}")
    public Group getOne(@PathVariable("name") String name){
        return groupService.getOne(name);
    }

    @GetMapping("/page/{name}")
    public Page<Group> pageOf(@PathVariable("name") String name,Page<Group> page){
        return groupService.pageOf(page,name);
    }

    @PostMapping("/add/{userId}/{groupId}")
    public Boolean addGroup(@PathVariable("userId") Long userId,
                            @PathVariable("groupId") Long groupId){
        return userGroupService.addGroup(userId,groupId);
    }

    @PostMapping("/remove/{userId}/{groupId}")
    public Boolean removeGroup(@PathVariable("userId") Long userId,
                            @PathVariable("groupId") Long groupId){
        return userGroupService.removeGroup(userId,groupId);
    }

    @Override
    protected IService<Group> service() {
        return groupService;
    }
}
