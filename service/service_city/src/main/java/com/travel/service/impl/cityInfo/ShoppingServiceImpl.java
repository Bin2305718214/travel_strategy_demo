package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.Shopping;
import com.travel.service.cityInfo.ShoppingService;
import com.travel.mapper.cityInfo.ShoppingMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
@Service
public class ShoppingServiceImpl extends ServiceImpl<ShoppingMapper, Shopping> implements ShoppingService{

    // 根据城市id获取指定信息
    @Override
    public Page<Shopping> getListByCityId(Long cityId, Long current, Long limit) {
        Page<Shopping> page = new Page<>(current, limit);

        LambdaQueryWrapper<Shopping> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Shopping::getCityId, cityId);

        Page<Shopping> shoppingPage = baseMapper.selectPage(page, wrapper);

        return shoppingPage;
    }

}




