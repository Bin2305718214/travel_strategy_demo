package com.travel.controller.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.Festival;
import com.travel.result.Result;
import com.travel.service.cityInfo.FestivalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-21 13:17
 */

@Api(tags = "节日管理")
@RestController
@RequestMapping("/admin/city/festival")
public class FestivalController {

    @Autowired
    private FestivalService festivalService;

    @ApiOperation(value = "分页查询节日信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageFestival(@PathVariable Long current,
                                     @PathVariable Long limit) {

        Page<Festival> festivalPage = festivalService.page(new Page<Festival>(current, limit));

        return Result.ok(festivalPage);
    }

    @ApiOperation(value = "获取指定id的节日信息")
    @PostMapping("get/{id}")
    public Result getFestival(@PathVariable Long id) {
        Festival festival = festivalService.getById(id);

        return Result.ok(festival);
    }

    @ApiOperation(value = "添加节日信息")
    @PostMapping("save")
    public Result saveFestival(@RequestBody Festival festival) {

        festival.setCreateTime(new Date());
        festival.setUpdateTime(new Date());

        boolean save = festivalService.save(festival);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改节日信息")
    @PostMapping("update")
    public Result updateFestival(@RequestBody Festival festival) {
        festival.setUpdateTime(new Date());

        boolean flag = festivalService.updateById(festival);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的节日信息")
    @DeleteMapping("remove/{id}")
    public Result removeFestival(@PathVariable Long id) {
        boolean flag = festivalService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
