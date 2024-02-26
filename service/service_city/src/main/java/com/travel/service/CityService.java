package com.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.city.City;
import com.travel.vo.CityQueryVo;

import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-18 14:48
 */
public interface CityService extends IService<City> {

    /**
     * 条件查询城市信息并分页
     * @param current 页码数
     * @param limit 每页数
     * @param cityQueryVo 查询条件
     * @return 分页城市信息
     */
    Page<City> selectCityPage(Long current, Long limit, CityQueryVo cityQueryVo);

    /**
     * 批量查询城市信息并分页
     * @param current 页码
     * @param limit 每页记录数
     * @param recordIdList 城市id列表
     * @return
     */
    Page<City> getCityPageByIds(Integer current, Integer limit, List<Long> recordIdList);
}
