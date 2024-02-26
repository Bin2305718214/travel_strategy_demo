package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.Hotel;
import com.travel.service.cityInfo.HotelService;
import com.travel.mapper.cityInfo.HotelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService{

    // 根据城市id获取指定信息
    @Override
    public Page<Hotel> getListByCityId(Long cityId, Long current, Long limit) {
        Page<Hotel> page = new Page<>(current, limit);

        LambdaQueryWrapper<Hotel> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Hotel::getCityId, cityId);

        Page<Hotel> hotelPage = baseMapper.selectPage(page, wrapper);

        return hotelPage;
    }

}




