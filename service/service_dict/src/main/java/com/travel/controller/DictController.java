package com.travel.controller;

import com.travel.model.dict.Dict;
import com.travel.result.Result;
import com.travel.service.DictService;
import com.travel.vo.CityQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-18 16:47
 */

@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据数据id查询子数据列表")
    @PostMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);

        return Result.ok(list);
    }

    @ApiOperation(value = "根据省代码和城市名称查询城市id列表")
    @PostMapping("findCityCodeList")
    public List findCityCodeList(@RequestBody CityQueryVo cityQueryVo) {
        List cityCodeList = dictService.selectCityList(cityQueryVo);

        return cityCodeList;
    }

    @ApiOperation(value = "根据dictCode获取下级节点")
    @PostMapping("findByDictCode/{dictCode}")
    public Result findByDictCode(@PathVariable String dictCode) {
        List<Dict> dictList = dictService.selectByDictCode(dictCode);

        return Result.ok(dictList);
    }

}
