package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.model.city.TelInfo;
import com.travel.service.cityInfo.TelInfoService;
import com.travel.mapper.cityInfo.TelInfoMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:07:47
 */
@Service
public class TelInfoServiceImpl extends ServiceImpl<TelInfoMapper, TelInfo> implements TelInfoService{

    // 根据实用信息id获取对应信息
    @Override
    public List<TelInfo> getByInfoId(Long infoId) {
        LambdaQueryWrapper<TelInfo> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(TelInfo::getInfoId, infoId);

        List<TelInfo> telInfos = baseMapper.selectList(wrapper);

        return telInfos;
    }
}




