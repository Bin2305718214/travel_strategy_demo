package com.travel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.discuss.PostPraise;

import java.util.List;

/**
 * @author Build_start
 */
public interface PostPraiseService extends IService<PostPraise> {

    /**
     * 通过用户id和游记id获取对应的点赞记录
     * @param userId 用户id
     * @param id 游记id
     * @return 点赞记录
     */
    PostPraise getPraiseByPostId(Long userId, Long id);

    /**
     * 通过用户id和游记id删除对应的点赞记录
     * @param userId 用户id
     * @param id 游记id
     */
    void deleteByUserIdAndPostId(Long userId, Long id);

    /**
     * 通过游记id删除对应的点赞记录
     * @param id 游记id
     * @return
     */
    Integer deleteByPostId(Long id);

    /**
     * 通过用户id获取点赞记录
     * @param userId 用户id
     * @return 点赞记录
     */
    List<PostPraise> getPraiseListByUserId(Long userId);
}
