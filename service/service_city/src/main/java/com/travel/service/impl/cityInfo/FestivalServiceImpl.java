package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.Festival;
import com.travel.service.cityInfo.FestivalService;
import com.travel.mapper.cityInfo.FestivalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
@Service
public class FestivalServiceImpl extends ServiceImpl<FestivalMapper, Festival> implements FestivalService{

    @Autowired
    private FestivalMapper festivalMapper;

    // 根据城市id获取指定信息
    @Override
    public Page<Festival> getListByCityId(Long cityId, Long current, Long limit) {
        Page<Festival> page = new Page<>(current, limit);

        LambdaQueryWrapper<Festival> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Festival::getCityId, cityId);

        Page<Festival> festivalPage = festivalMapper.selectPage(page, wrapper);

        return festivalPage;
    }
}




