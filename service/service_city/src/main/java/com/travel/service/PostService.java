package com.travel.service;

import com.travel.model.discuss.PostComment;
import com.travel.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author Build_start
 */
@FeignClient("service-user")
@Component
public interface PostService {

    /**
     * 获取游记总数和评论总数
     * @return
     */
    @PostMapping("/strategy/user/post/getCount")
    public Map<String, Integer> getCount();

    /**
     * 分页获取帖子信息
     * @param userId 用户id，不登录为-1
     * @param current 页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/strategy/user/post/findPage/{userId}/{current}/{limit}")
    public Result findPostPage(@PathVariable("userId") Long userId,
                               @PathVariable("current") Integer current,
                               @PathVariable("limit") Integer limit);

    /**
     * 获取指定游记信息
     * @param id
     * @return
     */
    @GetMapping("/strategy/user/post/get/{id}")
    public Result findPostById(@PathVariable("id") Long id);

    /**
     * 删除对应游记信息
     * @param id 游记id
     * @return
     */
    @PostMapping("/strategy/user/post/remove/{id}")
    public Result deletePost(@PathVariable("id") Long id);

    /**
     * 分页获取对应游记的评论信息
     * @param id 游记id
     * @param current 页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/strategy/user/post/getComment/{id}/{current}/{limit}")
    public Result findCommentByPostId(@PathVariable("id") Long id,
                                      @PathVariable("current") Integer current,
                                      @PathVariable("limit") Integer limit);

    /**
     * 删除对应的评论信息
     * @param postComment
     * @return
     */
    @PostMapping("/strategy/user/post/removeComment")
    public Result deleteComment(@RequestBody PostComment postComment);

    /**
     * 根据用户id获取游记id列表
     * @param userId
     * @return
     */
    @PostMapping("/strategy/user/post/findPostIds/{userId}")
    public List<Long> findPostIds(@PathVariable("userId") Long userId);
}
