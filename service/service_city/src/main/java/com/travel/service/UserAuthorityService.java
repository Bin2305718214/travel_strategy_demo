package com.travel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.user.UserAuthority;

/**
 * @author Build_start
 */
public interface UserAuthorityService extends IService<UserAuthority> {

    /**
     * 删除用户权限映射
     * @param id 用户id
     * @return
     */
    boolean deleteByUserId(Long id);
}
