package com.travel.controller.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.HospitalInfo;
import com.travel.model.city.PracticalInfo;
import com.travel.model.city.TelInfo;
import com.travel.result.Result;
import com.travel.service.cityInfo.HospitalInfoService;
import com.travel.service.cityInfo.PracticalInfoService;
import com.travel.service.cityInfo.TelInfoService;
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
 * @create 2022-12-21 13:17
 */

@Api(tags = "实用信息管理")
@RestController
@RequestMapping("/admin/city/practicalInfo")
public class PracticalInfoController {

    @Autowired
    private PracticalInfoService practicalInfoService;

    @Autowired
    private TelInfoService telInfoService;
    @Autowired
    private HospitalInfoService hospitalInfoService;

    @ApiOperation(value = "分页查询实用信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPagePracticalInfo(@PathVariable Long current,
                                     @PathVariable Long limit) {

        Page<PracticalInfo> practicalInfoPage = practicalInfoService.page(new Page<PracticalInfo>(current, limit));

        return Result.ok(practicalInfoPage);
    }

    @ApiOperation(value = "获取指定id的实用信息")
    @GetMapping("get/{id}")
    public Result getCity(@PathVariable Long id) {
        PracticalInfo practicalInfo = practicalInfoService.getById(id);

        return Result.ok(practicalInfo);
    }

    @ApiOperation(value = "获取指定id的实用信息详情")
    @PostMapping("getDetail/{id}")
    public Result getPracticalInfoDetail(@PathVariable Long id) {
        Map<String, Object> practicalInfoMap = new HashMap<>();

        PracticalInfo practicalInfo = practicalInfoService.getById(id);

        List<TelInfo> telInfos =  telInfoService.getByInfoId(id);
        List<HospitalInfo> hospitalInfos =  hospitalInfoService.getByInfoId(id);

        practicalInfoMap.put("PracticalInfo", practicalInfo);
        practicalInfoMap.put("TelInfos", telInfos);
        practicalInfoMap.put("HospitalInfos", hospitalInfos);

        return Result.ok(practicalInfoMap);
    }

    @ApiOperation(value = "添加实用信息")
    @PostMapping("save" + "test")
    public Result savePracticalInfo(@RequestBody PracticalInfo practicalInfo) {

        practicalInfo.setCreateTime(new Date());
        practicalInfo.setUpdateTime(new Date());

        boolean save = practicalInfoService.save(practicalInfo);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改实用设置")
    @PostMapping("update")
    public Result updatePracticalInfo(@RequestBody PracticalInfo practicalInfo) {
        practicalInfo.setUpdateTime(new Date());

        boolean flag = practicalInfoService.updateById(practicalInfo);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的景点信息")
    @DeleteMapping("remove/{id}")
    public Result removePracticalInfo(@PathVariable Long id) {
        boolean flag = practicalInfoService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
