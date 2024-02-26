package com.travel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.user.Authority;

import java.util.List;

/**
 * @author Build_start
 */
public interface AuthorityService extends IService<Authority> {

    /**
     * 获取权限列表
     * @return 权限列表
     */
    List<Authority> getAuthorityList();

}
