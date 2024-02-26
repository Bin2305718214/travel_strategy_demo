package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.AuthorityMapper;
import com.travel.mapper.UserAuthorityMapper;
import com.travel.mapper.UserMapper;
import com.travel.model.user.Authority;
import com.travel.model.user.User;
import com.travel.model.user.UserAuthority;
import com.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Build_start
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthorityMapper userAuthorityMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    // 分页获取用户信息
    @Override
    public Page<User> getUserPage(Integer current, Integer limit) {
        Page<User> page = new Page<>(current, limit);
        Page<User> userPage = null;

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        List<Long> userIds = getUserIds();
        // 添加查询条件（根据id批量查询用户信息）
        for (int i = 0; i < userIds.size(); i++) {
            wrapper.or().eq(User::getId, userIds.get(i));
        }

        if (userIds.size() != 0) {
            userPage = userMapper.selectPage(page, wrapper);

            // 添加角色
            for (int i = 0; i < userPage.getRecords().size(); i++) {
                userPage.getRecords().get(i).setRole(getRole(userPage.getRecords().get(i).getId()));
            }
        } else {
            userPage = page;
        }

        return userPage;
    }

    // 修改用户权限
    @Override
    public boolean updateUserAuthority(Long id, Long authorityId) {
        // 添加条件获取到用户权限映射关系
        LambdaQueryWrapper<UserAuthority> userAuthorityWrapper = new LambdaQueryWrapper<>();
        userAuthorityWrapper.eq(UserAuthority::getUserId, id);

        UserAuthority userAuthority = userAuthorityMapper.selectOne(userAuthorityWrapper);

        // 修改用户权限映射
        userAuthority.setAuthorityId(authorityId);
        int update = userAuthorityMapper.updateById(userAuthority);

        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 获取非超级管理员的用户id
    public List<Long> getUserIds() {
        LambdaQueryWrapper<UserAuthority> wrapper = new LambdaQueryWrapper<>();

        wrapper.ne(UserAuthority::getAuthorityId, new Long(3)).select(UserAuthority::getUserId);

        List<Long> userIds = new ArrayList<Long>();

        List<UserAuthority> userAuthorities = userAuthorityMapper.selectList(wrapper);

        for (UserAuthority userAuthority : userAuthorities) {
            userIds.add(userAuthority.getUserId());
        }

        return userIds;
    }

    // 根据用户id获取角色
    public String getRole(Long userId) {
        LambdaQueryWrapper<UserAuthority> userAuthorityWrapper = new LambdaQueryWrapper<>();

        userAuthorityWrapper.eq(UserAuthority::getUserId, userId);

        UserAuthority userAuthority = userAuthorityMapper.selectOne(userAuthorityWrapper);

        LambdaQueryWrapper<Authority> authorityWrapper = new LambdaQueryWrapper<>();

        authorityWrapper.eq(Authority::getId, userAuthority.getAuthorityId());

        Authority authority = authorityMapper.selectOne(authorityWrapper);

        return authority.getAuthority();
    }
}
