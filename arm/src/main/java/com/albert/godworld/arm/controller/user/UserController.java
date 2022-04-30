package com.albert.godworld.arm.controller.user;

import com.albert.godworld.arm.domain.user.User;
import com.albert.godworld.arm.service.user.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 添加User，传入User： userName,password
     */
    @PostMapping
    public User create(@RequestBody User user){
        userService.save(user);
        return user;
    }

    /**
     * 更新User，传入User：id, ...
     * @return true 如果更新成功，false 如果更新失败
     */
    @PutMapping
    public Boolean update(@RequestBody User user){
        return user.getId() != null && userService.updateById(user);
    }

    /**
     *
     * 根据id查早用户
     * @return User 如果查找成功，返回一个User，否则返回Null
     */
    @GetMapping("/id/{id}")
    public User getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    /**
     *
     * 根据Id列表查询用户信息
     * @param id 列表
     * @return 用户列表，如果查询不到，则返回一个空的List
     */
    @GetMapping("/ids")
    public List<User> getByIds(List<Long> id){
        return userService.listByIds(id);
    }

    /**
     *  根据名称查询用户
     * @return 分页用户数据
     */
    @GetMapping("/page/by_name/{name}")
    public Page<User> queryByName(@PathVariable("name") String name,Page<User> page){
        return userService.pageOf(page,name);
    }

    /**
     * 根据id删除用户
     * return true 如果删除成功，否则返回false
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return userService.removeById(id);
    }
}
