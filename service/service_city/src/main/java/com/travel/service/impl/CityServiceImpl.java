package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.service.DictService;
import com.travel.mapper.CityMapper;
import com.travel.model.city.City;
import com.travel.service.CityService;
import com.travel.vo.CityQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Build_start
 * @create 2022-12-18 14:49
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private DictService dictService;

    // 条件查询城市信息并分页
    @Override
    public Page<City> selectCityPage(Long current, Long limit, CityQueryVo cityQueryVo) {
        // 创建分页条件对象Pageable
        Page<City> page = new Page<>(current, limit);
        Page<City> cityPage = null;

        // 获取查询条件的城市id
        List cityIdList = dictService.findCityCodeList(cityQueryVo);

        LambdaQueryWrapper<City> wrapper = new LambdaQueryWrapper<>();

        if (null == cityIdList) {
            cityPage = cityMapper.selectPage(page, null);
        } else if (0 == cityIdList.size()) {
            cityPage = null;
        } else {
            for (int i = 0; i < cityIdList.size(); i++) {
                wrapper.or().eq(City::getId, cityIdList.get(i));
            }

            cityPage = cityMapper.selectPage(page, wrapper);
        }

        return cityPage;
    }

    // 批量查询城市信息并分页
    @Override
    public Page<City> getCityPageByIds(Integer current, Integer limit, List<Long> recordIdList) {
        Page<City> page = new Page<>();
        if (recordIdList.size() != 0) {
            LambdaQueryWrapper<City> wrapper = new LambdaQueryWrapper<>();

            for (int i = 0; i < recordIdList.size(); i++) {
                wrapper.or().eq(City::getId, recordIdList.get(i));
            }

            Page<City> cityPage = cityMapper.selectPage(page, wrapper);
            return cityPage;
        } else {
            return page;
        }

    }
}
