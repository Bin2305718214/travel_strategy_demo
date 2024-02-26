package com.travel.service;

import com.travel.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Build_start
 * @create 2022-12-24 21:15
 */

@FeignClient("service-dict")
@Service
public interface DictService {

    /**
     * 通过dictCode获取省列表
     * @param dictCode
     * @return
     */
    @PostMapping("/admin/dict/findByDictCode/{dictCode}")
    public Result findProvinceList(@PathVariable("dictCode") String dictCode);

    /**
     * 通过省id获取对应城市列表
     * @param id
     * @return
     */
    @PostMapping("/admin/dict/findChildData/{id}")
    public Result findCityList(@PathVariable("id") Long id);

}
