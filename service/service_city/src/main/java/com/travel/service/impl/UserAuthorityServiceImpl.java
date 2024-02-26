package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.UserAuthorityMapper;
import com.travel.model.user.UserAuthority;
import com.travel.service.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Build_start
 */
@Service
public class UserAuthorityServiceImpl extends ServiceImpl<UserAuthorityMapper, UserAuthority> implements UserAuthorityService {

    @Autowired
    private UserAuthorityMapper userAuthorityMapper;

    // 删除用户权限映射
    @Override
    public boolean deleteByUserId(Long id) {
        LambdaQueryWrapper<UserAuthority> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(UserAuthority::getUserId, id);

        int delete = userAuthorityMapper.delete(wrapper);

        if (delete > 1) {
            return true;
        } else {
            return false;
        }
    }
}
