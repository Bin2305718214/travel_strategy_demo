package com.travel.controller;

import com.travel.model.user.LoginUser;
import com.travel.result.Result;
import com.travel.service.MailService;
import com.travel.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Build_start
 */

@Api(tags = "登录管理")
@RestController
@RequestMapping("/strategy/user/login")
public class LoginController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "找回密码")
    @PostMapping("forgetPassword")
    public Result forgetPassword(@RequestBody LoginUser loginUser) {
        return userService.findPassword(loginUser);
    }

    @ApiOperation(value = "发送邮箱验证码")
    @PostMapping("code/email")
    public Result sendEmailCode(@RequestParam("email") String email) {
        return mailService.sendEmailCode(email);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public Result register(@RequestBody LoginUser loginUser) {
        return userService.register(loginUser);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginUser loginUser) {
        return userService.login(loginUser);
    }
}
