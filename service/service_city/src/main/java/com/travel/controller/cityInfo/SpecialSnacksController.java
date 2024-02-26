package com.travel.controller.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.SpecialSnacks;
import com.travel.result.Result;
import com.travel.service.cityInfo.SpecialSnacksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-21 13:17
 */

@Api(tags = "特色小吃管理")
@RestController
@RequestMapping("/admin/city/specialSnacks")
public class SpecialSnacksController {

    @Autowired
    private SpecialSnacksService specialSnacksService;

    @ApiOperation(value = "分页查询特色小吃信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageSpecialSnacks(@PathVariable Long current,
                                     @PathVariable Long limit) {

        Page<SpecialSnacks> specialSnacksPage = specialSnacksService.page(new Page<SpecialSnacks>(current, limit));

        return Result.ok(specialSnacksPage);
    }

    @ApiOperation(value = "获取指定id的特色小吃信息")
    @PostMapping("get/{id}")
    public Result getSpecialSnacks(@PathVariable Long id) {
        SpecialSnacks specialSnacks = specialSnacksService.getById(id);

        return Result.ok(specialSnacks);
    }

    @ApiOperation(value = "添加特色小吃信息")
    @PostMapping("save")
    public Result saveSpecialSnacks(@RequestBody SpecialSnacks specialSnacks) {

        specialSnacks.setCreateTime(new Date());
        specialSnacks.setUpdateTime(new Date());

        boolean save = specialSnacksService.save(specialSnacks);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改特色小吃信息")
    @PostMapping("update")
    public Result updateSpecialSnacks(@RequestBody SpecialSnacks specialSnacks) {
        specialSnacks.setUpdateTime(new Date());

        boolean flag = specialSnacksService.updateById(specialSnacks);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的特色小吃信息")
    @DeleteMapping("remove/{id}")
    public Result removeSpecialSnacks(@PathVariable Long id) {
        boolean flag = specialSnacksService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
