package com.travel.controller.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.Restaurant;
import com.travel.result.Result;
import com.travel.service.cityInfo.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-21 13:17
 */

@Api(tags = "餐馆管理")
@RestController
@RequestMapping("/admin/city/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @ApiOperation(value = "分页查询餐馆信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageRestaurant(@PathVariable Long current,
                                     @PathVariable Long limit) {

        Page<Restaurant> restaurantPage = restaurantService.page(new Page<Restaurant>(current, limit));

        return Result.ok(restaurantPage);
    }

    @ApiOperation(value = "获取指定id的餐馆信息")
    @PostMapping("get/{id}")
    public Result getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getById(id);

        return Result.ok(restaurant);
    }

    @ApiOperation(value = "添加餐馆信息")
    @PostMapping("save")
    public Result saveRestaurant(@RequestBody Restaurant restaurant) {

        restaurant.setCreateTime(new Date());
        restaurant.setUpdateTime(new Date());

        boolean save = restaurantService.save(restaurant);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改餐馆信息")
    @PostMapping("update")
    public Result updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurant.setUpdateTime(new Date());

        boolean flag = restaurantService.updateById(restaurant);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的餐馆信息")
    @DeleteMapping("remove/{id}")
    public Result removeRestaurant(@PathVariable Long id) {
        boolean flag = restaurantService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
