package com.travel.controller.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.ScenicSpot;
import com.travel.result.Result;
import com.travel.service.cityInfo.ScenicSpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-21 13:17
 */

@Api(tags = "景点管理")
@RestController
@RequestMapping("/admin/city/scenicSpot")
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    @ApiOperation(value = "分页查询景点信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageScenicSpot(@PathVariable Long current,
                                     @PathVariable Long limit) {

        Page<ScenicSpot> scenicSpotPage = scenicSpotService.page(new Page<ScenicSpot>(current, limit));

        return Result.ok(scenicSpotPage);
    }

    @ApiOperation(value = "获取指定id的景点信息")
    @PostMapping("get/{id}")
    public Result getScenicSpot(@PathVariable Long id) {
        ScenicSpot scenicSpot = scenicSpotService.getById(id);

        return Result.ok(scenicSpot);
    }

    @ApiOperation(value = "添加景点信息")
    @PostMapping("save")
    public Result saveScenicSpot(@RequestBody ScenicSpot scenicSpot) {

        scenicSpot.setCreateTime(new Date());
        scenicSpot.setUpdateTime(new Date());

        boolean save = scenicSpotService.save(scenicSpot);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改景点信息")
    @PostMapping("update")
    public Result updateScenicSpot(@RequestBody ScenicSpot scenicSpot) {
        scenicSpot.setUpdateTime(new Date());

        boolean flag = scenicSpotService.updateById(scenicSpot);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的景点信息")
    @DeleteMapping("remove/{id}")
    public Result removeScenicSpot(@PathVariable Long id) {
        boolean flag = scenicSpotService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
