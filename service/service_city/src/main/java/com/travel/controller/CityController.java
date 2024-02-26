package com.travel.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.*;
import com.travel.model.user.BrowseRecord;
import com.travel.result.Result;
import com.travel.service.*;
import com.travel.service.cityInfo.*;
import com.travel.vo.CityQueryVo;
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
 * @create 2022-12-18 14:51
 */

@Api(tags = "城市信息管理")
@RestController
@RequestMapping("/admin/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ScenicSpotService scenicSpotService;
    @Autowired
    private FestivalService festivalService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private SpecialSnacksService specialSnacksService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private PracticalInfoService practicalInfoService;
    @Autowired
    private TelInfoService telInfoService;
    @Autowired
    private HospitalInfoService hospitalInfoService;

    @Autowired
    private BrowseRecordService browseRecordService;

    @ApiOperation(value = "获取各个攻略的数量")
    @GetMapping("getAllCount")
    public Result getAllCount() {
        Map<String, Integer> map = new HashMap<>();

        int cityCount = cityService.count();
        int scenicSpotCount = scenicSpotService.count();
        int specialSnacksCount = specialSnacksService.count();
        int hotelCount = hotelService.count();
        int restaurantCount = restaurantService.count();
        int festivalCount = festivalService.count();
        int shoppingCount = shoppingService.count();
        int telICount = telInfoService.count();
        int hospCount = hospitalInfoService.count();

        map.put("cityCount", cityCount);
        map.put("scenicSpotCount", scenicSpotCount);
        map.put("specialSnacksCount", specialSnacksCount);
        map.put("hotelCount", hotelCount);
        map.put("restaurantCount", restaurantCount);
        map.put("festivalCount", festivalCount);
        map.put("shoppingCount", shoppingCount);
        map.put("telICount", telICount);
        map.put("hospCount", hospCount);

        return Result.ok(map);
    }

    @ApiOperation(value = "根据城市id批量分页获取城市信息")
    @PostMapping("findPageByIds/{current}/{limit}")
    public Page<City> getCityListByCityIds(@PathVariable("current") Integer current,
                                           @PathVariable("limit") Integer limit,
                                           @RequestBody List<Long> recordIdList) {
        Page<City> cityPage = cityService.getCityPageByIds(current, limit, recordIdList);

        return cityPage;
    }

    @ApiOperation(value = "条件查询城市信息并分页")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageCity(@PathVariable Long current,
                               @PathVariable Long limit,
                               @RequestBody(required = false) CityQueryVo cityQueryVo) {
        Page<City> cityPage = cityService.selectCityPage(current, limit, cityQueryVo);

        return Result.ok(cityPage);
    }

    @ApiOperation(value = "查询阅览量前十的城市信息")
    @PostMapping("findSort")
    public Result findSortByReadingNum() {
        LambdaQueryWrapper<City> wrapper = new LambdaQueryWrapper<>();

        wrapper.orderByDesc(City::getReadingNum).last("limit 10");

        List<City> list = cityService.list(wrapper);

        return Result.ok(list);
    }

    @ApiOperation(value = "获取指定id的城市信息")
    @PostMapping("get/{id}")
    public Result getCity(@PathVariable Long id) {
        City city = cityService.getById(id);

        return Result.ok(city);
    }

    @ApiOperation(value = "获取指定id的城市详情")
    @PostMapping("getCityDetail/{id}/{current}/{limit}")
    public Result getCityDetail(@PathVariable Long id, @PathVariable Long current, @PathVariable Long limit) {
        Map<String, Object> cityMap = new HashMap<>();

        City city = cityService.getById(id);

        Page<ScenicSpot> scenicSpots = scenicSpotService.getListByCityId(id, current, limit);
        Page<Festival> festivals = festivalService.getListByCityId(id, current, limit);
        Page<Hotel> hotels = hotelService.getListByCityId(id, current, limit);
        Page<SpecialSnacks> specialSnacks = specialSnacksService.getListByCityId(id, current, limit);
        Page<Restaurant> restaurants = restaurantService.getListByCityId(id, current, limit);
        Page<Shopping> shoppings = shoppingService.getListByCityId(id, current, limit);
        List practicalInfos = practicalInfoService.getListByCityId(id);

        cityMap.put("city", city);
        cityMap.put("scenicSpots", scenicSpots);
        cityMap.put("festivals", festivals);
        cityMap.put("hotels", hotels);
        cityMap.put("specialSnacks", specialSnacks);
        cityMap.put("restaurants", restaurants);
        cityMap.put("shoppings", shoppings);
        cityMap.put("practicalInfos", practicalInfos);

        return Result.ok(cityMap);
    }

    @ApiOperation(value = "添加城市信息")
    @PostMapping("save")
    public Result saveCity(@RequestBody City city) {

        city.setCreateTime(new Date());
        city.setUpdateTime(new Date());

        boolean save = cityService.save(city);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改城市信息")
    @PostMapping("update")
    public Result updateCity(@RequestBody City city) {
        city.setUpdateTime(new Date());

        boolean flag = cityService.updateById(city);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "批量逻辑删除城市信息")
    @DeleteMapping("batchRemove")
    public Result batchRemoveCity(@RequestBody List<Long> idList) {
        boolean delete = cityService.removeByIds(idList);

        if (delete) {
            for (int i = 0; i < idList.size(); i++) {
                browseRecordService.deleteBrowses(idList.get(i), BrowseRecord.CITY);
            }

            return Result.ok();
        } else {
            return Result.fail("批量删除失败！");
        }
    }

    @ApiOperation(value = "逻辑删除指定id的城市信息")
    @DeleteMapping("remove/{id}")
    public Result removeCity(@PathVariable Long id) {
        boolean flag = cityService.removeById(id);

        if (flag) {
            browseRecordService.deleteBrowses(id, BrowseRecord.CITY);
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
