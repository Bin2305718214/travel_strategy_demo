package com.travel.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.City;
import com.travel.model.discuss.Post;
import com.travel.model.index.Copywriting;
import com.travel.model.index.TreeHole;
import com.travel.model.user.BrowseRecord;
import com.travel.result.Result;
import com.travel.service.BrowseRecordService;
import com.travel.service.CityService;
import com.travel.service.IndexService;
import com.travel.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Build_start
 * @create 2022-12-23 16:03
 */

@Api(tags = "首页信息管理")
@RestController
@RequestMapping("/strategy/user/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation(value = "获取首页信息")
    @GetMapping("findIndexInfo/{current}/{limit}")
    public Result findIndexInfo(@PathVariable Long current, @PathVariable Long limit) {
        Map<String, Object> map = new HashMap<>();

        // 通过 Feign 服务调用获取首页数据
        List<Copywriting> copywritingList = (List<Copywriting>) indexService.findAllCopywriting().getData();
        Result result = indexService.findPageTreeHoleSort(current, limit);

        String jsonString = JSON.toJSONString(result.getData());
        Page<TreeHole> treeHolePage = JSON.parseObject(jsonString, Page.class);

        List<City> cityList = (List<City>) indexService.findSortByReadingNum().getData();

        map.put("copywritingList", copywritingList);
        map.put("treeHolePage", treeHolePage);
        map.put("cityList", cityList);

        return Result.ok(map);
    }

    @ApiOperation(value = "添加树洞信息")
    @PostMapping("saveTreeHole")
    public Result saveTreeHole(@RequestBody TreeHole treeHole) {
        return indexService.saveTreeHole(treeHole);
    }
}
