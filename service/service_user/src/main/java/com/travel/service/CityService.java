package com.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.City;
import com.travel.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-24 21:35
 */
@FeignClient("service-city")
@Service
public interface CityService {

    // 获取城市详情
    @PostMapping("/admin/city/getCityDetail/{id}/{current}/{limit}")
    public Result getCityDetail(@PathVariable("id") Long id, @PathVariable("current") Long current, @PathVariable("limit") Long limit);

    // 获取指定城市信息
    @PostMapping("/admin/city/get/{id}")
    public Result getCity(@PathVariable("id") Long id);

    // 修改指定城市信息
    @PostMapping("/admin/city/update")
    public Result updateCity(@RequestBody City city);

    // 获取节日详情
    @PostMapping("/admin/city/festival/get/{id}")
    public Result getFestival(@PathVariable("id") Long id);

    // 获取酒店详情
    @PostMapping("/admin/city/hotel/get/{id}")
    public Result getHotel(@PathVariable("id") Long id);

    // 获取实用信息详情
    @PostMapping("/admin/city/practicalInfo/getDetail/{id}")
    public Result getPracticalInfoDetail(@PathVariable("id") Long id);

    // 获取餐馆详情
    @PostMapping("/admin/city/restaurant/get/{id}")
    public Result getRestaurant(@PathVariable("id") Long id);

    // 获取景点详情
    @PostMapping("/admin/city/scenicSpot/get/{id}")
    public Result getScenicSpot(@PathVariable("id") Long id);

    // 获取购物点详情
    @PostMapping("/admin/city/shopping/get/{id}")
    public Result getShopping(@PathVariable("id") Long id);

    // 获取特色小吃详情
    @PostMapping("/admin/city/specialSnacks/get/{id}")
    public Result getSpecialSnacks(@PathVariable("id") Long id);

    // 根据城市id批量分页获取城市信息
    @PostMapping("/admin/city/findPageByIds/{current}/{limit}")
    public Page<City> getCityListByCityIds(@PathVariable("current") Integer current,
                                           @PathVariable("limit") Integer limit,
                                           @RequestBody List<Long> recordIdList);
}
