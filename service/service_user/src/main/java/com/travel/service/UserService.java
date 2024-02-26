package com.travel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.user.LoginUser;
import com.travel.model.user.User;
import com.travel.model.user.UserInfo;
import com.travel.result.Result;

/**
 * @author Build_start
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param loginUser 注册信息
     */
    Result register(LoginUser loginUser);

    /**
     * 用户登录
     * @param loginUser
     */
    Result login(LoginUser loginUser);

    /**
     * 找回密码
     * @param loginUser
     */
    Result findPassword(LoginUser loginUser);

    /**
     * 修改用户信息
     * @param userInfo 用户信息
     * @return
     */
    String updateUserInfo(UserInfo userInfo);
}
