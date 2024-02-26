package com.travel.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.model.city.City;
import com.travel.model.dict.Dict;
import com.travel.model.discuss.Post;
import com.travel.model.user.BrowseRecord;
import com.travel.result.Result;
import com.travel.service.BrowseRecordService;
import com.travel.service.CityService;
import com.travel.service.DictService;
import com.travel.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

/**
 * @author Build_start
 * @create 2022-12-23 16:53
 */

@Api(tags = "攻略信息管理")
@RestController
@RequestMapping("/strategy/user/strategy")
public class StrategyController {

    @Autowired
    private DictService dictService;
    @Autowired
    private CityService cityService;

    @ApiOperation(value = "获取省列表信息")
    @GetMapping("findProvinceList/{dictCode}")
    public Result findProvinceList(@PathVariable String dictCode) {
        Map<String, Object> map = new HashMap<>();

        List<Dict> provinceList = (List<Dict>) dictService.findProvinceList(dictCode).getData();
        List<Dict> cityList = (List<Dict>) dictService.findCityList(new Long(110000)).getData();

        map.put("provinceList", provinceList);
        map.put("cityList", cityList);

        return Result.ok(map);
    }

    @ApiOperation(value = "获取省对应的城市列表信息")
    @GetMapping("findCityList/{id}")
    public Result findCityList(@PathVariable Long id) {
        List<Dict> cityList = (List<Dict>) dictService.findCityList(id).getData();

        return Result.ok(cityList);
    }

    @ApiOperation(value = "获取对应的城市详情")
    @GetMapping("findCityDetail/{id}/{current}/{limit}")
    public Result findCityDetail(@PathVariable("id") Long id,
                                @PathVariable("current") Long current,
                                @PathVariable("limit") Long limit) {
        Map<String, Object> cityMap = (Map<String, Object>) cityService.getCityDetail(id, current, limit).getData();

        return Result.ok(cityMap);
    }

    @ApiOperation(value = "获取城市对应信息详情")
    @GetMapping("findDetail/{id}")
    public Result findDetail(@PathVariable Long id) {
        Integer current = Math.toIntExact(id / 10000000);

        Result result = null;
        if (current == 1) {
            result = cityService.getScenicSpot(id);
        } else if (current == 2){
            result = cityService.getFestival(id);
        } else if (current == 3){
            result = cityService.getHotel(id);
        } else if (current == 4){
            result = cityService.getSpecialSnacks(id);
        } else if (current == 5){
            result = cityService.getRestaurant(id);
        } else if (current == 6){
            result = cityService.getShopping(id);
        } else if (current == 7){
            result = cityService.getPracticalInfoDetail(id);
        } else if (current == 8){
            result = cityService.getFestival(id);
        }

        LinkedHashMap<String, Object> detail = (LinkedHashMap<String, Object>) result.getData();

        Integer cityId = (Integer) detail.get("cityId");

        String cityStr = JSONObject.toJSONString(cityService.getCity(new Long(cityId)).getData());
        City city = JSONObject.parseObject(cityStr, City.class);

        Map<String, Object> map = new HashMap<>();
        map.put("detail", detail);
        map.put("city",city);

        return Result.ok(map);
    }
}
