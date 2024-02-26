package com.travel.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.index.TreeHole;
import com.travel.result.Result;
import com.travel.service.TreeHoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-22 17:14
 */

@Api(tags = "树洞管理")
@RestController
@RequestMapping("/admin/city/treehole")
public class TreeHoleController {

    @Autowired
    private TreeHoleService treeHoleService;

    @ApiOperation(value = "获取树洞总数")
    @GetMapping("getCount")
    public Result getTreeHoleCount() {
        Integer treeholeCount = treeHoleService.count();

        return Result.ok(treeholeCount);
    }

    @ApiOperation(value = "分页查询树洞信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageTreeHole(@PathVariable Long current,
                                      @PathVariable Long limit) {

        Page<TreeHole> treeHolePage = treeHoleService.page(new Page<TreeHole>(current, limit));

        return Result.ok(treeHolePage);
    }

    @ApiOperation(value = "分页查询树洞信息")
    @PostMapping("findPageSort/{current}/{limit}")
    public Result findPageTreeHoleSort(@PathVariable Long current,
                                      @PathVariable Long limit) {

        LambdaQueryWrapper<TreeHole> wrapper = new LambdaQueryWrapper<>();

        wrapper.orderByDesc(TreeHole::getCreateTime);

        Page<TreeHole> treeHolePage = treeHoleService.page(new Page<TreeHole>(current, limit), wrapper);

        return Result.ok(treeHolePage);
    }

    @ApiOperation(value = "添加树洞信息")
    @PostMapping("save")
    public Result saveTreeHole(@RequestBody TreeHole treeHole) {
        treeHole.setCreateTime(new Date());
        treeHole.setUpdateTime(new Date());

        boolean save = treeHoleService.save(treeHole);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的树洞信息")
    @DeleteMapping("remove/{id}")
    public Result removeTreeHole(@PathVariable Long id) {
        boolean flag = treeHoleService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}
