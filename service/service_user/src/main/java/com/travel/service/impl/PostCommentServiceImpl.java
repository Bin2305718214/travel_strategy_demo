package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.PostCommentMapper;
import com.travel.model.discuss.PostComment;
import com.travel.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Build_start
 */
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment> implements PostCommentService {

    @Autowired
    private PostCommentMapper postCommentMapper;

    // 通过游记id分页获取评论
    @Override
    public Page<PostComment> findComments(Long id, Integer current, Integer limit) {
        Page<PostComment> page = new Page<>(current, limit);

        LambdaQueryWrapper<PostComment> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PostComment::getPostId, id);

        Page<PostComment> postCommentPage = postCommentMapper.selectPage(page, wrapper);

        return postCommentPage;
    }

    // 通过游记id删除对应的评论
    @Override
    public Integer deleteByPostId(Long id) {
        LambdaQueryWrapper<PostComment> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PostComment::getPostId, id);

        int delete = postCommentMapper.delete(wrapper);

        return delete;
    }
}
