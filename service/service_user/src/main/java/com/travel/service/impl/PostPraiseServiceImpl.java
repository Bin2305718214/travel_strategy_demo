package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.PostPraiseMapper;
import com.travel.model.discuss.PostPraise;
import com.travel.service.PostPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Build_start
 */
@Service
public class PostPraiseServiceImpl extends ServiceImpl<PostPraiseMapper, PostPraise> implements PostPraiseService {

    @Autowired
    private PostPraiseMapper postPraiseMapper;

    // 通过用户id和游记id获取对应的点赞记录
    @Override
    public PostPraise getPraiseByPostId(Long userId, Long id) {
        LambdaQueryWrapper<PostPraise> wrapper = new LambdaQueryWrapper<>();

        // 添加查询条件
        wrapper.eq(PostPraise::getUserId, userId).eq(PostPraise::getPostId, id);

        PostPraise postPraise = postPraiseMapper.selectOne(wrapper);

        return postPraise;
    }

    // 通过用户id和游记id删除对应的点赞记录
    @Override
    public void deleteByUserIdAndPostId(Long userId, Long id) {
        LambdaQueryWrapper<PostPraise> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PostPraise::getUserId, userId).eq(PostPraise::getPostId, id);

        postPraiseMapper.delete(wrapper);
    }

    // 通过游记id删除对应的点赞记录
    @Override
    public Integer deleteByPostId(Long id) {
        LambdaQueryWrapper<PostPraise> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PostPraise::getPostId, id);

        int delete = postPraiseMapper.delete(wrapper);

        return delete;
    }

    // 通过用户id获取点赞记录
    @Override
    public List<PostPraise> getPraiseListByUserId(Long userId) {
        LambdaQueryWrapper<PostPraise> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PostPraise::getUserId, userId).select(PostPraise::getPostId);

        List<PostPraise> postPraiseList = postPraiseMapper.selectList(wrapper);

        return postPraiseList;
    }
}
