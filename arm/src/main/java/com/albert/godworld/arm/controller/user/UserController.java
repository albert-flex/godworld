package com.albert.godworld.arm.controller.user;

import com.albert.godworld.arm.controller.CurdController;
import com.albert.godworld.arm.domain.user.User;
import com.albert.godworld.arm.service.user.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends CurdController<User> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/one/{name}")
    public User getUserOne(@PathVariable("name") String name){
        return userService.loadFromUserName(name);
    }

    @GetMapping("/page/{name}")
    public Page<User> page(@PathVariable("name") String name,Page<User> page){
        return userService.pageOf(page,name);
    }

    @Override
    protected IService<User> service() {
        return userService;
    }
}
