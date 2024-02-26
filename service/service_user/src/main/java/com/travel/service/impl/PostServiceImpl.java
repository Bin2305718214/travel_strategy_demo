package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.PostMapper;
import com.travel.model.discuss.Post;
import com.travel.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Build_start
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private PostMapper postMapper;

    // 根据创建时间倒叙分页获取游记信息
    @Override
    public Page<Post> pageOrderByTimeDesc(Integer current, Integer limit) {
        Page<Post> page = new Page<>(current, limit);

        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Post::getCreateTime);

        Page<Post> postPage = postMapper.selectPage(page, wrapper);

        return postPage;
    }

    // 通过用户id分页查询游记信息
    @Override
    public Page<Post> getPostPage(Long userId, Integer current, Integer limit) {
        Page<Post> page = new Page<>(current, limit);

        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getUserId, userId);

        Page<Post> postPage = postMapper.selectPage(page, wrapper);

        return postPage;
    }

    // 根据游记id列表分页获取游记列表
    @Override
    public Page<Post> getPostListByPostIds(List<Long> postIdList, Integer current, Integer limit) {
        Page<Post> page = new Page<>(current, limit);
        if (postIdList.size() != 0) {

            LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();

            for (int i = 0; i < postIdList.size(); i++) {
                wrapper.or().eq(Post::getId, postIdList.get(i));
            }

            Page<Post> postPage = postMapper.selectPage(page, wrapper);

            return postPage;
        } else {
            return page;
        }
    }

    // 根据用户id获取游记列表
    @Override
    public List<Post> getPostByUserId(Long userId) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Post::getUserId, userId).select(Post::getId);

        List<Post> postList = postMapper.selectList(wrapper);

        return postList;
    }
}
