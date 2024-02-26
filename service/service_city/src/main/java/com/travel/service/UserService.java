package com.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.user.LoginUser;
import com.travel.model.user.User;
import com.travel.result.Result;

/**
 * @author Build_start
 */
public interface UserService extends IService<User> {

    /**
     * 分页获取用户信息
     * @param current 页码
     * @param limit 每页记录数
     * @return
     */
    Page<User> getUserPage(Integer current, Integer limit);

    /**
     * 修改用户权限
     * @param id 用户id
     * @param authorityId 修改的权限的id
     * @return
     */
    boolean updateUserAuthority(Long id, Long authorityId);
}
