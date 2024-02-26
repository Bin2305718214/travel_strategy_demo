package com.travel.controller.cityInfo;

import com.travel.model.city.TelInfo;
import com.travel.result.Result;
import com.travel.service.cityInfo.TelInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-21 13:17
 */

@Api(tags = "电话信息管理")
@RestController
@RequestMapping("/admin/city/telTnfo")
public class TelInfoController {

    @Autowired
    private TelInfoService telTnfoService;

    @ApiOperation(value = "查询所有电话信息信息")
    @PostMapping("findAll")
    public Result findAllTelInfo() {

        List<TelInfo> telInfoList = telTnfoService.list();

        return Result.ok(telInfoList);
    }

    @ApiOperation(value = "获取指定id的电话信息信息")
    @GetMapping("get/{id}")
    public Result getTelInfo(@PathVariable Long id) {
        TelInfo telTnfo = telTnfoService.getById(id);

        return Result.ok(telTnfo);
    }

    @ApiOperation(value = "添加电话信息信息")
    @PostMapping("save")
    public Result saveTelInfo(@RequestBody TelInfo telTnfo) {

        telTnfo.setCreateTime(new Date());
        telTnfo.setUpdateTime(new Date());

        boolean save = telTnfoService.save(telTnfo);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改电话信息信息")
    @PostMapping("update")
    public Result updateTelInfo(@RequestBody TelInfo telTnfo) {
        telTnfo.setUpdateTime(new Date());

        boolean flag = telTnfoService.updateById(telTnfo);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的电话信息信息")
    @DeleteMapping("remove/{id}")
    public Result removeTelInfo(@PathVariable Long id) {
        boolean flag = telTnfoService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
