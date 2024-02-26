package com.travel.service.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.city.Restaurant;

import java.util.List;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
public interface RestaurantService extends IService<Restaurant> {

    /**
     * 根据城市id获取指定信息
     * @param cityId 城市id
     * @return
     */
    Page<Restaurant> getListByCityId(Long cityId, Long current, Long limit);
}
