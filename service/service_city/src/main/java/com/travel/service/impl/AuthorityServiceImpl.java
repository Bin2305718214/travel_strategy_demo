package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.AuthorityMapper;
import com.travel.model.user.Authority;
import com.travel.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Build_start
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    // 获取权限列表
    @Override
    public List<Authority> getAuthorityList() {
        LambdaQueryWrapper<Authority> wrapper = new LambdaQueryWrapper<>();

        wrapper.ne(Authority::getId, 3);

        List<Authority> authorities = authorityMapper.selectList(wrapper);

        return authorities;
    }
}
