package com.travel.service;

import com.travel.model.index.TreeHole;
import com.travel.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Build_start
 * @create 2022-12-23 15:43
 */

@FeignClient("service-city")
@Service
public interface IndexService {

    @PostMapping("/admin/city/copywriting/findAll")
    public Result findAllCopywriting();

    @PostMapping("/admin/city/treehole/findPageSort/{current}/{limit}")
    public Result findPageTreeHoleSort(@PathVariable("current") Long current, @PathVariable("limit") Long limit);

    @PostMapping("/admin/city/findSort")
    public Result findSortByReadingNum();

    @PostMapping("/admin/city/treehole/save")
    public Result saveTreeHole(@RequestBody TreeHole treeHole);

}
