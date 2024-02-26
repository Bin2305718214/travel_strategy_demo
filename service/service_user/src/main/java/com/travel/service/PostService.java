package com.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.discuss.Post;

import java.util.List;

/**
 * @author Build_start
 */
public interface PostService extends IService<Post> {

    /**
     * 根据创建时间倒叙分页获取游记信息
     * @param current 页码
     * @param limit 每页记录数
     * @return
     */
    Page<Post> pageOrderByTimeDesc(Integer current, Integer limit);

    /**
     * 通过用户id分页查询游记信息
     * @param userId 用户id
     * @param current 页码
     * @param limit 每页记录数
     * @return
     */
    Page<Post> getPostPage(Long userId, Integer current, Integer limit);

    /**
     * 根据游记id列表分页获取游记列表
     * @param postIdList 游记列表
     * @param current 页码
     * @param limit 每页记录数
     * @return
     */
    Page<Post> getPostListByPostIds(List<Long> postIdList, Integer current, Integer limit);

    /**
     * 根据用户id获取游记列表
     * @param userId 用户id
     * @return
     */
    List<Post> getPostByUserId(Long userId);
}
