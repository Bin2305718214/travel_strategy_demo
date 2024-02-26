package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.Restaurant;
import com.travel.service.cityInfo.RestaurantService;
import com.travel.mapper.cityInfo.RestaurantMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
@Service
public class RestaurantServiceImpl extends ServiceImpl<RestaurantMapper, Restaurant> implements RestaurantService{

    // 根据城市id获取指定信息
    @Override
    public Page<Restaurant> getListByCityId(Long cityId, Long current, Long limit) {
        Page<Restaurant> page = new Page<>(current, limit);

        LambdaQueryWrapper<Restaurant> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Restaurant::getCityId, cityId);

        Page<Restaurant> restaurantPage = baseMapper.selectPage(page, wrapper);

        return restaurantPage;
    }

}




