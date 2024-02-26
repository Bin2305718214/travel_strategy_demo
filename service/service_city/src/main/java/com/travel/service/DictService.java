package com.travel.service;

import com.travel.vo.CityQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-19 12:35
 */
@Component
@FeignClient("service-dict")
public interface DictService {

    @PostMapping("/admin/dict/findCityCodeList")
    public List findCityCodeList(@RequestBody CityQueryVo cityQueryVo);

}
