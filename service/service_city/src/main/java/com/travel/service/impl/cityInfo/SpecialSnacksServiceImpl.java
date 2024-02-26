package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.SpecialSnacks;
import com.travel.service.cityInfo.SpecialSnacksService;
import com.travel.mapper.cityInfo.SpecialSnacksMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
@Service
public class SpecialSnacksServiceImpl extends ServiceImpl<SpecialSnacksMapper, SpecialSnacks> implements SpecialSnacksService{

    // 根据城市id获取指定信息
    @Override
    public Page<SpecialSnacks> getListByCityId(Long cityId, Long current, Long limit) {
        Page<SpecialSnacks> page = new Page<>(current, limit);

        LambdaQueryWrapper<SpecialSnacks> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(SpecialSnacks::getCityId, cityId);

        Page<SpecialSnacks> specialSnacksPage = baseMapper.selectPage(page, wrapper);

        return specialSnacksPage;
    }

}




