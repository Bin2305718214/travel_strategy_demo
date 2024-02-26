package com.travel.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.AuthorityMapper;
import com.travel.mapper.UserAuthorityMapper;
import com.travel.mapper.UserMapper;
import com.travel.model.user.*;
import com.travel.result.Result;
import com.travel.result.ResultCodeEnum;
import com.travel.service.UserService;
import com.travel.utils.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * 用户注册登录
 * @author Build_start
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private UserAuthorityMapper userAuthorityMapper;

    @Autowired
    private RedisTemplate redisTemplate;



    // 注册
    @Override
    public Result register(LoginUser loginUser) {
        // 获取参数
        String nickName = loginUser.getNickName();
        String avatarUrl = loginUser.getAvatarUrl();
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        String code = loginUser.getCode();

        User user = getUserByEmail(email);

        // 查询用户，是否存在
        if (null != user) {
            return Result.build(201, "该用户已注册");
        }

        // 获取正确的验证码
        String rightCode = redisTemplate.opsForValue().get("EMAIL_" + email).toString();
        if (!code.equals(rightCode)) {
            // 验证码比对
            return Result.build(201, "验证码错误");
        }

        // 删除验证码
        redisTemplate.delete("EMAIL_" + email);

        LambdaQueryWrapper<Authority> authWrapper = new LambdaQueryWrapper<>();
        authWrapper.eq(Authority::getId, 2);

        Authority authority = authorityMapper.selectOne(authWrapper);

        user = new User();
        // 获取加密盐
        String salt = this.randomEncryptedSalt();
        // 用户名
        user.setNickName(nickName);
        // 用户头像
        if (null == avatarUrl) {
            user.setAvatarUrl("2.png");
        } else {
            user.setAvatarUrl(avatarUrl);
        }
        // 邮箱
        user.setEmail(email);
        // 密码加密（原明文密码 + 随机加密盐） md5加密
        user.setPassword(DigestUtils.md5Hex(password + salt));
        // 设置可用状态
        user.setStatus(1);
        // 加密盐
        user.setSalt(salt);
        // 添加权限
        user.setRole(authority.getAuthority());

        userMapper.insert(user);

        user = getUserByEmail(email);

        UserAuthority userAuthority = new UserAuthority();

        userAuthority.setUserId(user.getId());
        userAuthority.setAuthorityId(authority.getId());

        userAuthorityMapper.insert(userAuthority);

        String userJson = JSONObject.toJSONString(user);
        String token = JwtUtil.createJWT(userJson, null);

        return Result.ok(token);
    }

    // 登录
    @Override
    public Result login(LoginUser loginUser) {
        // 获取参数
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);

        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            return Result.build(201, "该用户不存在！");
        } else if (user.getStatus() == 0) {
            return Result.build(201, "账户不可用，如有异议请联系管理员！");
        }

        // 获取加密盐
        String salt = user.getSalt();

        wrapper.clear();
        wrapper.eq(User::getEmail, email);
        wrapper.eq(User::getPassword, DigestUtils.md5Hex(password + salt));

        // 查询用户
        user = userMapper.selectOne(wrapper);

        if (user == null) {
            return Result.build(201, "邮箱或密码错误");
        }

        // 获取并添加权限到返回值中
        LambdaQueryWrapper<UserAuthority> uaWrapper = new LambdaQueryWrapper<>();
        uaWrapper.eq(UserAuthority::getUserId, user.getId());
        UserAuthority userAuthority = userAuthorityMapper.selectOne(uaWrapper);
        Authority authority = authorityMapper.selectById(userAuthority.getAuthorityId());

        user.setRole(authority.getAuthority());

        UserInfo userInfo = changeUser(user);

        String userInfoJson = JSONObject.toJSONString(userInfo);
        String token = JwtUtil.createJWT(userInfoJson, null);

        return Result.ok(token);
    }

    // 找回密码
    @Override
    public Result findPassword(LoginUser loginUser) {
        User user = getUserByEmail(loginUser.getEmail());

        if (null == user) {
            return Result.build(201, "用户不存在！");
        } else {
            // 获取正确的验证码
            String rightCode = redisTemplate.opsForValue().get("EMAIL_" + loginUser.getEmail()).toString();
            if (!loginUser.getCode().equals(rightCode)) {
                // 验证码比对
                return Result.build(201, "验证码错误");
            }

            // 删除验证码
            redisTemplate.delete("EMAIL_" + loginUser.getEmail());

            user.setPassword(DigestUtils.md5Hex(loginUser.getPassword() + user.getSalt()));

            int updateFlag = userMapper.updateById(user);

            if (updateFlag > 0) {
                return Result.ok();
            } else {
                return Result.fail(ResultCodeEnum.UPDATE_FAIL);
            }
        }
    }

    // 修改用户信息
    @Override
    public String updateUserInfo(UserInfo userInfo) {
        User user = userMapper.selectById(userInfo.getId());

        user.setAvatarUrl(userInfo.getAvatarUrl());
        user.setNickName(userInfo.getNickName());
        user.setAutograph(userInfo.getAutograph());

        int updateFlag = userMapper.updateById(user);

        // 修改成功后返回用户信息
        if (updateFlag > 0) {

            return toTokenString(changeUser(user));
        } else {
            return null;
        }
    }

    /**
     * 通过邮箱获取用户
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(User::getEmail, email);

        User user = userMapper.selectOne(wrapper);

        return user;
    }

    /**
     * 将对象转换为token
     * @param obj
     * @return
     */
    public String toTokenString(Object obj) {
        String objJson = JSONObject.toJSONString(obj);
        String token = JwtUtil.createJWT(objJson, null);

        return token;
    }

    /**
     * 将 User 转换为 UserInfo，隐去不安全信息
     * @param user
     * @return
     */
    public UserInfo changeUser(User user) {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setAvatarUrl(user.getAvatarUrl());
        userInfo.setNickName(user.getNickName());
        userInfo.setAutograph(user.getAutograph());
        userInfo.setRole(user.getRole());

        return userInfo;
    }

    /**
     * 随机生成盐
     */
    public String randomEncryptedSalt() {
        return RandomStringUtils.randomAlphabetic(4) + "#!$@";
    }
}
