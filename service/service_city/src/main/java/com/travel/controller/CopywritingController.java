package com.travel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.index.Copywriting;
import com.travel.result.Result;
import com.travel.service.CopywritingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-22 17:06
 */

@Api(tags = "文案管理")
@RestController
@RequestMapping("/admin/city/copywriting")
public class CopywritingController {
    
    @Autowired
    private CopywritingService copywritingService;

    @ApiOperation(value = "查询所有文案信息")
    @PostMapping("findAll")
    public Result findAllCopywriting() {

        List<Copywriting> copywritingList = copywritingService.list();

        return Result.ok(copywritingList);
    }

    @ApiOperation(value = "分页查询文案信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageCopywriting(@PathVariable Long current,
                                   @PathVariable Long limit) {

        Page<Copywriting> copywritingPage = copywritingService.page(new Page<Copywriting>(current, limit));

        return Result.ok(copywritingPage);
    }

    @ApiOperation(value = "获取指定id的文案信息")
    @GetMapping("get/{id}")
    public Result getCopywriting(@PathVariable Long id) {
        Copywriting copywriting = copywritingService.getById(id);

        return Result.ok(copywriting);
    }

    @ApiOperation(value = "添加文案信息")
    @PostMapping("save")
    public Result saveCopywriting(@RequestBody Copywriting copywriting) {
        copywriting.setCreateTime(new Date());
        copywriting.setUpdateTime(new Date());

        boolean save = copywritingService.save(copywriting);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改文案信息")
    @PostMapping("update")
    public Result updateCopywriting(@RequestBody Copywriting copywriting) {
        copywriting.setUpdateTime(new Date());

        boolean flag = copywritingService.updateById(copywriting);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的文案信息")
    @DeleteMapping("remove/{id}")
    public Result removeCopywriting(@PathVariable Long id) {
        boolean flag = copywritingService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    
}
