package com.albert.godworld.arm.resource.controller.user;

import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.dto.RegisterRequire;
import com.albert.godworld.arm.resource.dto.UserResponse;
import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.other.CaptchaService;
import com.albert.godworld.arm.resource.service.social.SocialInfoService;
import com.albert.godworld.arm.resource.service.user.UserService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PrincipalConvert convert;
    private final AuthorService authorService;
    private final SocialInfoService socialInfoService;

    private final PasswordEncoder passwordEncoder;
    private final CaptchaService captchaService;

    @Autowired
    public UserController(UserService userService, PrincipalConvert convert,
                          AuthorService authorService, SocialInfoService socialInfoService,
                          PasswordEncoder passwordEncoder, CaptchaService captchaService) {
        this.userService = userService;
        this.convert = convert;
        this.authorService = authorService;
        this.socialInfoService = socialInfoService;
        this.passwordEncoder = passwordEncoder;
        this.captchaService = captchaService;
    }

    /**
     * 添加User，传入User： userName,password
     */
    @PostMapping
    public User create(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    /**
     * 更新User，传入User：id, ...
     *
     * @return true 如果更新成功，false 如果更新失败
     */
    @PutMapping
    public Boolean update(@RequestBody User user) {
        return user.getId() != null && userService.updateById(user);
    }

    /**
     * 根据id查早用户
     *
     * @return User 如果查找成功，返回一个User，否则返回Null
     */
    @GetMapping("/id/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    /**
     * 根据Id列表查询用户信息
     *
     * @param id 列表
     * @return 用户列表，如果查询不到，则返回一个空的List
     */
    @GetMapping("/ids")
    public List<User> getByIds(List<Long> id) {
        return userService.listByIds(id);
    }


    @GetMapping("/check/{name}")
    public Boolean checkName(@PathVariable("name") String name) {
        return userService.checkNameAvail(name);
    }

    /**
     * 根据名称查询用户
     *
     * @return 分页用户数据
     */
    @GetMapping("/page/by_name/{name}")
    public Page<User> queryByName(@PathVariable("name") String name, Page<User> page) {
        return userService.pageOf(page, name);
    }

    @GetMapping("/page")
    public Page<User> pageOfAll(Page<User> page) {
        return userService.page(page);
    }

    /**
     * 根据id删除用户
     * return true 如果删除成功，否则返回false
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        return userService.removeById(id);
    }

    @GetMapping("/info")
    public UserResponse get(Principal principal) {
        if (principal == null) return null;

        UserResponse response = new UserResponse();
        User user = convert.convert(principal);
        response.setUserId(user.getId());
        response.setUserName(user.getUsername());
        response.setEmail(user.getEmail());
        Long authorId = authorService.getAuthorIdByUserId(user.getId());
        if (authorId != null) {
            response.setAuthorId(authorId);
            SocialInfo info = socialInfoService.getByAuthor(authorId);
            if (info != null) {
                response.setSocialId(info.getId());
            }
        }
        return response;
    }

    @PostMapping("/register")
    public RV<User> register(@RequestBody RegisterRequire require) {

        //检查用户名唯一性
        if(!userService.checkNameAvail(require.getUserName())){
            return RVError.USERNAME_NOT_AVAIL.to();
        }

        //检查邮件唯一性
        if(!userService.checkEmailAvail(require.getEmail())){
            return RVError.EMAIL_NOT_AVAIL.to();
        }

        //检查验证码是否正确
        if(!captchaService.checkCaptcha(require.getCaptcha(),require.getEmail())){
            return RVError.CAPTCHA_NOT_CORRECT.to();
        }

        User user=require.convert();
        user.setPassword(passwordEncoder.encode(require.getPassword()));
        if(!userService.save(user)){
            return RVError.USER_INSERT_ERROR.to();
        }

        return RV.success(user);
    }
}
