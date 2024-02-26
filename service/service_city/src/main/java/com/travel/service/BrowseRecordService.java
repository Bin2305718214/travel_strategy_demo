package com.travel.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Build_start
 */
@Component
@FeignClient("service-user")
public interface BrowseRecordService {

    // 通过记录id和记录类型删除对应的浏览记录
    @PostMapping("/strategy/user/browse/deleteBrowse/{id}/{type}")
    public Integer deleteBrowses(@RequestParam("id") Long id, @RequestParam("type") String type);

    // 通过用户id删除浏览记录
    @PostMapping("/strategy/user/browse/deleteByUserId/{id}")
    public Integer deleteByUserId(@RequestParam("id") Long userId);
}
