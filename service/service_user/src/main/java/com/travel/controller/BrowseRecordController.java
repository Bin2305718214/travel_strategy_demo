package com.travel.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.model.city.City;
import com.travel.model.discuss.Post;
import com.travel.model.user.BrowseRecord;
import com.travel.result.Result;
import com.travel.service.BrowseRecordService;
import com.travel.service.CityService;
import com.travel.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 */
@Api(tags = "浏览记录管理")
@RestController
@RequestMapping("/strategy/user/browse")
public class BrowseRecordController {

    @Autowired
    private CityService cityService;
    @Autowired
    private PostService postService;
    @Autowired
    private BrowseRecordService browseRecordService;

    @ApiOperation(value = "通过用户id删除访问记录")
    @PostMapping("/deleteByUserId/{id}")
    public Integer deleteByUserId(@RequestParam("id") Long userId) {
        Integer delete = browseRecordService.deleteByUserId(userId);

        return delete;
    }

    @ApiOperation(value = "批量删除访问记录")
    @PostMapping("/deleteBrowse/{id}/{type}")
    public Integer deleteBrowses(@RequestParam("id") Long id, @RequestParam("type") String type) {
        Integer delete = browseRecordService.deleteByRecordId(id, type);

        return delete;
    }

    @ApiOperation(value = "添加用户浏览记录")
    @PostMapping("addUserBrowse/{userId}/{browseId}/{type}")
    public Result userBrowser(@PathVariable("userId") Long userId,
                              @PathVariable("browseId") Long browseId,
                              @PathVariable("type") String type) {

        BrowseRecord browseRecord = browseRecordService.findBrowseRecordByUserIdAndRecordId(userId, browseId, type);

        if (null == browseRecord) {
            // 添加浏览记录
            browseRecord = new BrowseRecord();
            browseRecord.setUserId(userId);
            browseRecord.setBrowseId(browseId);
            browseRecord.setBrowseType(type);
            browseRecord.setCreateTime(new Date());
            browseRecord.setUpdateTime(new Date());

            boolean flag = browseRecordService.save(browseRecord);

            if (flag) {
                if (BrowseRecord.CITY.equals(type)) {
                    // 获取并修改城市访问量
                    Object data = cityService.getCity(browseId).getData();
                    String cityStr = JSONObject.toJSONString(data);
                    City city = JSONObject.parseObject(cityStr, City.class);

                    city.setReadingNum(city.getReadingNum() + 1);
                    cityService.updateCity(city);
                } else {
                    Post post = postService.getById(browseId);

                    post.setReadingNum(post.getReadingNum() + 1);
                    postService.updateById(post);
                }

                return Result.ok();
            } else {
                return Result.fail();
            }
        } else {
            return Result.ok();
        }
    }

}
