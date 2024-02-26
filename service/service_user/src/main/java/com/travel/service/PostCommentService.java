package com.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.discuss.PostComment;

import java.util.List;

/**
 * @author Build_start
 */
public interface PostCommentService extends IService<PostComment> {

    /**
     * 通过游记id分页获取评论
     * @param id 游记id
     * @param current 页码数
     * @param limit 每页记录数
     * @return
     */
    Page<PostComment> findComments(Long id, Integer current, Integer limit);

    /**
     * 通过游记id删除对应的评论
     * @param id 游记id
     */
    Integer deleteByPostId(Long id);
}
