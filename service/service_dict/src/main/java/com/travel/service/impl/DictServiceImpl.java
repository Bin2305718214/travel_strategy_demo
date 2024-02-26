package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.DictMapper;
import com.travel.model.dict.Dict;
import com.travel.service.DictService;
import com.travel.vo.CityQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-18 17:02
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;

    // 根据数据id查询子数据列表
    @Override
    @Cacheable(value = "cityList", keyGenerator = "keyGenerator")
    public List<Dict> findChildData(Long id) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getParentId, id);

        List<Dict> dictList = dictMapper.selectList(wrapper);

        // 向list集合每个dict对象中设置hasChildren
        for (Dict dict : dictList) {
            dict.setHasChildren(isChildren(dict.getId()));
        }

        return dictList;
    }

    // 根据省代码和城市名称查询城市id列表
    @Override
    public List selectCityList(CityQueryVo cityQueryVo) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(null != cityQueryVo.getProvinceCode(), Dict::getParentId, cityQueryVo.getProvinceCode())
                .like(StringUtils.isNotBlank(cityQueryVo.getName()), Dict::getName, cityQueryVo.getName());

        List<Dict> dictList = dictMapper.selectList(wrapper);

        List cityCodeList = new ArrayList();
        if (dictList.size() > 100) {
            cityCodeList = null;
        } else {
            for (Dict dict : dictList) {
                cityCodeList.add(dict.getId());
            }
        }

        return cityCodeList;
    }

    // 根据dictCode获取下级节点
    @Override
    @Cacheable(value = "provinceList", keyGenerator = "keyGenerator")
    public List<Dict> selectByDictCode(String dictCode) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();

        // 根据dictCode获取的对应的父节点的id
        Dict parentDict = this.getParentDict(wrapper, dictCode);
        wrapper.clear();
        // 通过父节点的id获取全部子节点
        List<Dict> dictList = this.findChildData(parentDict.getId());

        return dictList;
    }

    // 获取查询子节点条件
    private Dict getParentDict(LambdaQueryWrapper<Dict> wrapper, String dictCode) {
        wrapper.eq(Dict::getDictCode, dictCode);

        Dict dict = baseMapper.selectOne(wrapper);

        return dict;
    }

    // 判断id下面是否有子节点
    private boolean isChildren(Long id) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getParentId, id);

        Integer count = dictMapper.selectCount(wrapper);

        return (count > 0);
    }
}
