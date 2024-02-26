package com.travel.service.cityInfo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.city.TelInfo;

import java.util.List;

/**
 * @author Build_start
 * @createDate 2022-12-18 15:07:47
 */
public interface TelInfoService extends IService<TelInfo> {

    /**
     * 根据实用信息id获取对应信息
     * @param infoId 实用信息id
     * @return
     */
    List<TelInfo> getByInfoId(Long infoId);
}
