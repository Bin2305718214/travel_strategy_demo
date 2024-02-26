package com.travel.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.discuss.Post;
import com.travel.model.discuss.PostComment;
import com.travel.result.Result;
import com.travel.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Build_start
 */
@Api(tags = "游记管理")
@RestController
@RequestMapping("/admin/city/post")
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation(value = "删除对应id的游记评论")
    @PostMapping("removePostComment")
    public Result removePostComment(@RequestBody PostComment postComment) {
        return postService.deleteComment(postComment);
    }

    @ApiOperation(value = "获取游记详情")
    @GetMapping("findPostDetail/{id}/{current}/{limit}")
    public Result findPostDetail(@PathVariable("id") Long id,
                                 @PathVariable("current") Integer current,
                                 @PathVariable("limit") Integer limit) {
        Map<String, Object> map = new HashMap<>();

        // 转换游记对象
        Object postObj = postService.findPostById(id).getData();
        String postStr = JSONObject.toJSONString(postObj);
        Post post = JSONObject.parseObject(postStr, Post.class);

        //postImgs = Arrays.stream(post.getPostImgs().split(",")).collect(Collectors.toList());

        // 转换游记评论对象
        Object commentsObj = postService.findCommentByPostId(id, current, limit).getData();
        String commmentsStr = JSONObject.toJSONString(commentsObj);
        Page<PostComment> postCommentPage = JSONObject.parseObject(commmentsStr, Page.class);

        map.put("post", post);
        map.put("postComments", postCommentPage);

        return Result.ok(map);
    }

    @ApiOperation(value = "删除对应id的游记")
    @PostMapping("removePost/{id}")
    public Result removePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }

    @ApiOperation(value = "分页查询游记列表")
    @GetMapping("findPostPage/{current}/{limit}")
    public Result findPostPage(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit) {
        Result postPage = postService.findPostPage(new Long(-1), current, limit);

        return Result.ok(postPage);
    }

    @ApiOperation(value = "获取游记和评论总数")
    @GetMapping("getCount")
    public Result getPostAndCommentCount() {
        Map<String, Integer> map = postService.getCount();

        return Result.ok(map);
    }

}
