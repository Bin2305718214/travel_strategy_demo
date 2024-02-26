package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.HospitalInfo;
import com.travel.service.cityInfo.HospitalInfoService;
import com.travel.mapper.cityInfo.HospitalInfoMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:09:09
 */
@Service
public class HospitalInfoServiceImpl extends ServiceImpl<HospitalInfoMapper, HospitalInfo> implements HospitalInfoService{

    // 根据实用信息id获取对应信息
    @Override
    public List<HospitalInfo> getByInfoId(Long infoId) {
        LambdaQueryWrapper<HospitalInfo> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(HospitalInfo::getInfoId, infoId);

        List<HospitalInfo> hospitalInfos = baseMapper.selectList(wrapper);

        return hospitalInfos;
    }
}




