package com.travel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.user.Authority;
import com.travel.model.user.BrowseRecord;
import com.travel.model.user.User;
import com.travel.result.Result;
import com.travel.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * @author Build_start
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/city/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthorityService userAuthorityService;
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private BrowseRecordService browseRecordService;

    @Autowired
    private PostService postService;

    @ApiOperation(value = "删除对应用户")
    @PostMapping("/remove/{id}")
    public Result removeUser(@PathVariable("id") Long id) {
        boolean flag = userService.removeById(id);

        if (flag) {
            // 删除用户权限的映射记录
            userAuthorityService.deleteByUserId(id);
            // 删除浏览记录
            browseRecordService.deleteByUserId(id);
            // 获取到用发布的游记并循环删除
            List<Long> postIds = postService.findPostIds(id);
            for (Long postId : postIds) {
                postService.deletePost(postId);
            }

            return Result.ok();
        } else {
            return Result.build(201, "删除用户失败");
        }
    };

    @ApiOperation(value = "改用户状态")
    @PostMapping("/changeStatus/{id}")
    public Result changeStatus(@PathVariable("id") Long id) {
        User user = userService.getById(id);

        if (user.getStatus() == 0) {
            user.setStatus(1);
        } else {
            user.setStatus(0);
        }
        user.setUpdateTime(new Date());

        boolean updateFlag = userService.updateById(user);

        if (updateFlag) {
            return Result.ok();
        } else {
            return Result.build(201, "状态修改失败！");
        }
    };

    @ApiOperation(value = "修改用户权限")
    @PostMapping("/changeUserRole/{id}/{authorityId}")
    public Result updateUserRole (@PathVariable("id") Long id,
                                  @PathVariable("authorityId") Long authorityId) {
        boolean flag = userService.updateUserAuthority(id, authorityId);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail("修改用户权限失败！");
        }
    };

    @ApiOperation(value = "获取权限列表（除超级管理员外）")
    @GetMapping("/findAuthority")
    public Result findAuthority() {
        List<Authority> authorityList = authorityService.getAuthorityList();

        return Result.ok(authorityList);
    };

    @ApiOperation(value = "分页获取用户信息")
    @GetMapping("/findPage/{current}/{limit}")
    public Result findUserPage(@PathVariable("current") Integer current,
                               @PathVariable("limit") Integer limit) {

        Page<User> userPage = userService.getUserPage(current, limit);

        return Result.ok(userPage);
    };

    @ApiOperation(value = "获取用户总数")
    @GetMapping("/getCount")
    public Result getUserCount() {
        Integer userCount = userService.count();

        return Result.ok(userCount);
    };
}
