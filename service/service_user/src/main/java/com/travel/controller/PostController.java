package com.travel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.discuss.Post;
import com.travel.model.discuss.PostComment;
import com.travel.model.discuss.PostPraise;
import com.travel.model.user.BrowseRecord;
import com.travel.result.Result;
import com.travel.service.BrowseRecordService;
import com.travel.service.PostCommentService;
import com.travel.service.PostPraiseService;
import com.travel.service.PostService;
import com.travel.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Build_start
 */
@Api(tags = "讨论管理")
@RestController
@RequestMapping("/strategy/user/post")
public class PostController {

    @Value("${travel-strategy.path}")
    private String basePath;

    @Autowired
    private PostService postService;
    @Autowired
    private PostPraiseService postPraiseService;
    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private BrowseRecordService browseRecordService;

    @ApiOperation(value = "获取游记总数和评论总数")
    @PostMapping("/getCount")
    public Map<String, Integer> getCount() {
        Map<String, Integer> map = new HashMap<>();

        int postCount = postService.count();
        int postCommentCount = postCommentService.count();

        map.put("postCount", postCount);
        map.put("postCommentCount", postCommentCount);

        return map;
    }

    @ApiOperation(value = "根据用户id获取游记id列表")
    @PostMapping("/findPostIds/{userId}")
    public List<Long> findPostIds(@PathVariable("userId") Long userId) {
        List<Post> postList = postService.getPostByUserId(userId);

        List<Long> postIdList = new ArrayList<>();

        for (Post post : postList) {
            postIdList.add(post.getId());
        }

        return postIdList;
    }

    @ApiOperation(value = "删除对应评论信息")
    @PostMapping("/removeComment")
    public Result deleteComment(@RequestBody PostComment postComment) {
        boolean flag = postCommentService.removeById(postComment.getId());

        if (flag) {
            Post post = postService.getById(postComment.getPostId());
            post.setCommentsNum(post.getCommentsNum() - 1);
            postService.updateById(post);

            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "添加评论信息")
    @PostMapping("/saveComment")
    public Result addPostComment(@RequestBody PostComment postComment) {
        postComment.setCreateTime(new Date());
        postComment.setUpdateTime(new Date());

        boolean flag = postCommentService.save(postComment);

        if (flag) {
            Post post = postService.getById(postComment.getPostId());
            post.setCommentsNum(post.getCommentsNum() + 1);
            postService.updateById(post);

            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "删除对应游记信息")
    @PostMapping("/remove/{id}")
    public Result deletePost(@PathVariable("id") Long id) {
        List<String> postImgs = new ArrayList<>();

        Post post = postService.getById(id);

        postImgs = Arrays.stream(post.getPostImgs().split(",")).collect(Collectors.toList());
        postImgs.add(post.getCoverImg());

        boolean flag = postService.removeById(id);

        if (flag) {
            postCommentService.deleteByPostId(id);
            postPraiseService.deleteByPostId(id);

            browseRecordService.deleteByRecordId(id, BrowseRecord.POST);

            FileUtils.deleteAll(basePath, postImgs);

            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "获取指定的游记的评论信息")
    @GetMapping("/getComment/{id}/{current}/{limit}")
    public Result findCommentByPostId(@PathVariable("id") Long id,
                               @PathVariable("current") Integer current,
                               @PathVariable("limit") Integer limit) {
        // 获取游记的评论
        Page<PostComment> postCommentPage = postCommentService.findComments(id, current, limit);

        // 循环遍历添加评论的父级用户名
        PostComment postComment = new PostComment();
        for (int i = 0; i <postCommentPage.getRecords().size(); i++) {
            postComment = postCommentPage.getRecords().get(i);

            // 当父级id不为0（回复评论）
            if (postComment.getParentId() != 0) {
                PostComment parentComment = postCommentService.getById(postComment.getParentId());

                // 当评论删除时
                if (null == parentComment) {
                    // 将父级id置为 -1
                    postComment.setParentId(new Long(-1));
                } else {
                    postComment.setParentUserName(parentComment.getNickName());
                }

                postCommentPage.getRecords().remove(i);
                postCommentPage.getRecords().add(i, postComment);
            }
        }

        return Result.ok(postCommentPage);
    }

    @ApiOperation(value = "获取指定的游记信息")
    @GetMapping("/get/{id}")
    public Result findPostById(@PathVariable("id") Long id) {
        Post post = postService.getById(id);

        return Result.ok(post);
    }

    @ApiOperation(value = "保存游记信息")
    @PostMapping("/save")
    public Result savePost(@RequestBody Post post) {
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());

        boolean save = postService.save(post);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "改变点赞状态")
    @PostMapping("/changeLikeStatus/{userId}/{postId}")
    public Result changeLikeStatus(@PathVariable("userId") Long userId,
                                   @PathVariable("postId") Long postId,
                                   @RequestBody List<Post> postList) {
        Post post = new Post();

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getId() == postId) {
                // 已点赞时，点赞数 -1 ，删除点赞记录
                if (postList.get(i).getIsPraise()) {
                    postList.get(i).setPraiseNum(postList.get(i).getPraiseNum() - 1);

                    postPraiseService.deleteByUserIdAndPostId(userId, postId);
                } else {    // 未点赞时，点赞数 +1 ，添加点赞记录
                    postList.get(i).setPraiseNum(postList.get(i).getPraiseNum() + 1);

                    PostPraise postPraise = new PostPraise();

                    postPraise.setUserId(userId);
                    postPraise.setPostId(postId);
                    postPraise.setCreateTime(new Date());
                    postPraise.setUpdateTime(new Date());

                    postPraiseService.save(postPraise);
                }

                postList.get(i).setIsPraise(!postList.get(i).getIsPraise());
                post = postList.get(i);

                break;
            }
        }

        postService.updateById(post);

        return Result.ok(postList);
    }

    @ApiOperation(value = "分页查询游记信息")
    @GetMapping("/findPage/{userId}/{current}/{limit}")
    public Result findPostPage(@PathVariable("userId") Long userId,
                               @PathVariable("current") Integer current,
                               @PathVariable("limit") Integer limit) {
        Page<Post> postPage = postService.pageOrderByTimeDesc(current, limit);

        List<Post> postList = postPage.getRecords();

        List<Post> newPostList = new ArrayList<>();

        // 是否为登录状态
        if (userId > 0) {
            PostPraise postPraise = null;
            for (Post post : postList) {
                postPraise = postPraiseService.getPraiseByPostId(userId, post.getId());

                // 是否有点赞记录
                if (postPraise != null) {
                    post.setIsPraise(true);
                }
                newPostList.add(post);
            }
        } else {
            newPostList = postList;
        }

        postPage.setRecords(newPostList);

        return Result.ok(postPage);
    }

}
