package com.travel.controller.cityInfo;

import com.travel.model.city.HospitalInfo;
import com.travel.result.Result;
import com.travel.service.cityInfo.HospitalInfoService;
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

@Api(tags = "医院信息管理")
@RestController
@RequestMapping("/admin/city/hospitalInfo")
public class HospitalInfoController {

    @Autowired
    private HospitalInfoService hospitalInfoService;

    @ApiOperation(value = "查询所有医院信息信息")
    @PostMapping("findAll")
    public Result findAllHospitalInfo() {

        List<HospitalInfo> hospitalInfoList = hospitalInfoService.list();

        return Result.ok(hospitalInfoList);
    }

    @ApiOperation(value = "获取指定id的医院信息信息")
    @GetMapping("get/{id}")
    public Result getHospitalInfo(@PathVariable Long id) {
        HospitalInfo hospitalInfo = hospitalInfoService.getById(id);

        return Result.ok(hospitalInfo);
    }

    @ApiOperation(value = "添加医院信息信息")
    @PostMapping("save")
    public Result saveHospitalInfo(@RequestBody HospitalInfo hospitalInfo) {

        hospitalInfo.setCreateTime(new Date());
        hospitalInfo.setUpdateTime(new Date());

        boolean save = hospitalInfoService.save(hospitalInfo);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改医院信息信息")
    @PostMapping("update")
    public Result updateHospitalInfo(@RequestBody HospitalInfo hospitalInfo) {
        hospitalInfo.setUpdateTime(new Date());

        boolean flag = hospitalInfoService.updateById(hospitalInfo);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的医院信息信息")
    @DeleteMapping("remove/{id}")
    public Result removeHospitalInfo(@PathVariable Long id) {
        boolean flag = hospitalInfoService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
