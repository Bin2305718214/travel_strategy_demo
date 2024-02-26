package com.travel.service.cityInfo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.city.HospitalInfo;

import java.util.List;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:09:09
 */
public interface HospitalInfoService extends IService<HospitalInfo> {

    /**
     * 根据实用信息id获取对应信息
     * @param infoId 实用信息id
     * @return
     */
    List<HospitalInfo> getByInfoId(Long infoId);
}
