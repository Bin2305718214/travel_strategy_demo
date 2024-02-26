package com.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.mapper.BrowseRecordMapper;
import com.travel.model.user.BrowseRecord;
import com.travel.service.BrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Build_start
 */
@Service
public class BrowseRecordServiceImpl extends ServiceImpl<BrowseRecordMapper, BrowseRecord> implements BrowseRecordService {

    @Autowired
    private BrowseRecordMapper browseRecordMapper;

    // 通过用户id和城市id查询浏览记录
    @Override
    public BrowseRecord findBrowseRecordByUserIdAndRecordId(Long userId, Long cityId, String type) {
        
        LambdaQueryWrapper<BrowseRecord> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BrowseRecord::getUserId, userId).eq(BrowseRecord::getBrowseId, cityId).eq(BrowseRecord::getBrowseType, type);

        BrowseRecord browseRecord = browseRecordMapper.selectOne(wrapper);

        return browseRecord;
    }

    // 通过帖子id删除对应的浏览记录
    @Override
    public Integer deleteByRecordId(Long id, String type) {
        LambdaQueryWrapper<BrowseRecord> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BrowseRecord::getBrowseId, id).eq(BrowseRecord::getBrowseType, type);

        int delete = browseRecordMapper.delete(wrapper);

        return delete;
    }

    // 通过用户id和记录类型获取浏览记录列表
    @Override
    public List<BrowseRecord> getRecordListByUserId(Long userId, String type) {
        LambdaQueryWrapper<BrowseRecord> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BrowseRecord::getUserId, userId).eq(BrowseRecord::getBrowseType, type).select(BrowseRecord::getBrowseId);

        List<BrowseRecord> browseRecordList = browseRecordMapper.selectList(wrapper);

        return browseRecordList;
    }

    // 通过用户id删除访问记录
    @Override
    public Integer deleteByUserId(Long userId) {
        LambdaQueryWrapper<BrowseRecord> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BrowseRecord::getUserId, userId);

        int delete = browseRecordMapper.delete(wrapper);

        return delete;
    }
}
