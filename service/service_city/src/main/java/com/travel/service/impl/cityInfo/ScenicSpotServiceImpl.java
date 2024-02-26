package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.ScenicSpot;
import com.travel.service.cityInfo.ScenicSpotService;
import com.travel.mapper.cityInfo.ScenicSpotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements ScenicSpotService{

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    // 根据城市id获取指定信息
    @Override
    public Page<ScenicSpot> getListByCityId(Long cityId, Long current, Long limit) {
        Page<ScenicSpot> page = new Page<>(current, limit);

        LambdaQueryWrapper<ScenicSpot> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(ScenicSpot::getCityId, cityId);

        Page<ScenicSpot> scenicSpotPage = scenicSpotMapper.selectPage(page, wrapper);

        return scenicSpotPage;
    }

}




