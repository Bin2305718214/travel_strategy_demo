package com.travel.service.impl.cityInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.cityInfo.HospitalInfoMapper;
import com.travel.mapper.cityInfo.TelInfoMapper;
import com.travel.model.city.*;
import com.travel.service.cityInfo.PracticalInfoService;
import com.travel.mapper.cityInfo.PracticalInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:16:15
 */
@Service
public class PracticalInfoServiceImpl extends ServiceImpl<PracticalInfoMapper, PracticalInfo> implements PracticalInfoService{

    @Autowired
    private HospitalInfoMapper hospitalInfoMapper;
    @Autowired
    private TelInfoMapper telInfoMapper;

    // 根据城市id获取指定信息
    @Override
    public List getListByCityId(Long cityId) {
        LambdaQueryWrapper<PracticalInfo> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PracticalInfo::getCityId, cityId);

        List<PracticalInfo> list = baseMapper.selectList(wrapper);
        List practicalInfos = new ArrayList();
        for (PracticalInfo practicalInfo : list) {
            if ("医院".equals(practicalInfo.getName())) {
                LambdaQueryWrapper<HospitalInfo> hospitalWrapper = new LambdaQueryWrapper<>();

                hospitalWrapper.eq(HospitalInfo::getInfoId, practicalInfo.getId());

                List<HospitalInfo> hospitalInfos = hospitalInfoMapper.selectList(hospitalWrapper);

                Practical practical = this.toPractical(practicalInfo, hospitalInfos);

                // 添加到返回结果中
                practicalInfos.add(practical);

            } else if ("电话".equals(practicalInfo.getName())) {
                LambdaQueryWrapper<TelInfo> telWrapper = new LambdaQueryWrapper<>();

                telWrapper.eq(TelInfo::getInfoId, practicalInfo.getId());

                List<TelInfo> telInfos = telInfoMapper.selectList(telWrapper);

                Practical practical = this.toPractical(practicalInfo, telInfos);

                // 添加到返回结果中
                practicalInfos.add(practical);
            } else {
                practicalInfos.add(practicalInfo);
            }
        }

        return practicalInfos;
    }

    // 将PracticalInfo类转换为Practical类并添加子节点
    public Practical toPractical (PracticalInfo practicalInfo, List child) {
        Practical practical = new Practical();
        practical.setId(practicalInfo.getId());
        practical.setCityId(practicalInfo.getCityId());
        practical.setName(practicalInfo.getName());
        practical.setContent(practicalInfo.getContent());

        practical.setChild(child);

        return practical;
    }

}




