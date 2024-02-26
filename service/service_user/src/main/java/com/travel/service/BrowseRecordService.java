package com.travel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.user.BrowseRecord;

import java.util.List;

/**
 * @author Build_start
 */
public interface BrowseRecordService extends IService<BrowseRecord> {

    /**
     * 通过用户id和城市id查询浏览记录
     * @param userId 用户id
     * @param cityId 城市id
     * @param type 类型
     * @return
     */
    public BrowseRecord findBrowseRecordByUserIdAndRecordId(Long userId, Long cityId, String type);

    /**
     * 通过记录id删除对应的浏览记录
     * @param id 记录id
     * @param type 记录类型
     * @return
     */
    Integer deleteByRecordId(Long id, String type);

    /**
     * 通过用户id和记录类型获取浏览记录列表
     * @param userId 用户id
     * @param type 记录类型
     * @return
     */
    List<BrowseRecord> getRecordListByUserId(Long userId, String type);

    /**
     * 通过用户id删除访问记录
     * @param userId 用户id
     * @return
     */
    Integer deleteByUserId(Long userId);
}
