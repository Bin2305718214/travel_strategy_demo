package com.travel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.City;
import com.travel.model.discuss.Post;
import com.travel.model.discuss.PostPraise;
import com.travel.model.user.BrowseRecord;
import com.travel.model.user.User;
import com.travel.model.user.UserInfo;
import com.travel.model.user.UserPwd;
import com.travel.result.Result;
import com.travel.result.ResultCodeEnum;
import com.travel.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Build_start
 */
@Api(tags = "个人中心信息管理")
@RestController
@RequestMapping("/strategy/user/personal")
public class PersonalCenterController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostPraiseService postPraiseService;
    @Autowired
    private BrowseRecordService browseRecordService;
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePwd")
    public Result updatePassword(@RequestBody UserPwd userPwd) {
        User user = userService.getById(userPwd.getId());

        if (user.getPassword().equals(DigestUtils.md5Hex(userPwd.getOldPassword() + user.getSalt()))) {
            user.setPassword(DigestUtils.md5Hex(userPwd.getNewPassword() + user.getSalt()));
            boolean flag = userService.updateById(user);

            if (flag) {
                return Result.ok();
            } else {
                return Result.fail(ResultCodeEnum.UPDATE_FAIL);
            }
        } else {
            return Result.build(201, "原始密码错误！");
        }
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody UserInfo userInfo) {
        String token = userService.updateUserInfo(userInfo);

        if (token != null) {
            return Result.ok(token);
        } else {
            return Result.fail(ResultCodeEnum.UPDATE_FAIL);
        }
    }

    @ApiOperation(value = "分页查询用户浏览的游记")
    @GetMapping("/findBrowsePostPage/{userId}/{type}/{current}/{limit}")
    public Result findBrowsePostPage(@PathVariable("userId") Long userId,
                                   @PathVariable("type") String type,
                                   @PathVariable("current") Integer current,
                                   @PathVariable("limit") Integer limit) {
        List<BrowseRecord> browseRecordList = browseRecordService.getRecordListByUserId(userId, type);

        Page recordPage = null;

        List<Long> recordIdList = new ArrayList<>();

        for (BrowseRecord browseRecord : browseRecordList) {
            recordIdList.add(browseRecord.getBrowseId());
        }

        if (type.equals(BrowseRecord.CITY)) {
            recordPage = cityService.getCityListByCityIds(current, limit, recordIdList);
        } else {
            recordPage = postService.getPostListByPostIds(recordIdList, current, limit);

            Page<Post> postPage = new Page<>();

            postPage = recordPage;

            for (int i = 0; i < recordPage.getRecords().size(); i++) {
                PostPraise postPraise = postPraiseService.getPraiseByPostId(userId, postPage.getRecords().get(i).getId());

                if (postPraise != null) {
                    postPage.getRecords().get(i).setIsPraise(true);
                }
            }

            recordPage = postPage;
        }

        return Result.ok(recordPage);
    }

    @ApiOperation(value = "分页查询用户点赞的游记")
    @GetMapping("findPraisePostPage/{userId}/{current}/{limit}")
    public Result findPraisePostPage(@PathVariable("userId") Long userId,
                                     @PathVariable("current") Integer current,
                                     @PathVariable("limit") Integer limit) {
        List<PostPraise> postPraiseList = postPraiseService.getPraiseListByUserId(userId);

        List<Long> postIdList = new ArrayList<>();

        for (PostPraise postPraise : postPraiseList) {
            postIdList.add(postPraise.getPostId());
        }

        Page<Post> postPage = postService.getPostListByPostIds(postIdList, current, limit);

        for (int i = 0; i < postPage.getRecords().size(); i++) {
            postPage.getRecords().get(i).setIsPraise(true);
        }

        return Result.ok(postPage);
    }

    @ApiOperation(value = "分页查询用户发布的游记")
    @GetMapping("/findUserPostPage/{userId}/{current}/{limit}")
    public Result findUserPostPage(@PathVariable("userId") Long userId,
                              @PathVariable("current") Integer current,
                              @PathVariable("limit") Integer limit) {
        Page<Post> postPage = postService.getPostPage(userId, current, limit);

        for (int i = 0; i < postPage.getRecords().size(); i++) {
            PostPraise postPraise = postPraiseService.getPraiseByPostId(userId, postPage.getRecords().get(i).getId());

            if (postPraise != null) {
                postPage.getRecords().get(i).setIsPraise(true);
            }
        }

        return Result.ok(postPage);
    }
}
