package com.travel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.model.dict.Dict;
import com.travel.vo.CityQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-18 17:02
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据数据id查询子数据列表
     * @param id 根id
     * @return 子数据列表
     */
    List<Dict> findChildData(Long id);

    /**
     * 根据省代码和城市名称查询城市id列表
     * @param cityQueryVo
     * @return
     */
    List selectCityList(CityQueryVo cityQueryVo);

    /**
     * 根据dictCode获取下级节点
     * @param dictCode
     * @return
     */
    List<Dict> selectByDictCode(String dictCode);
}
