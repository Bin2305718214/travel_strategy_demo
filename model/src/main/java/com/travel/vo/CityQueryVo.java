package com.travel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Build_start
 * @create 2022-12-18 15:38
 */

@Data
public class CityQueryVo {

    @ApiModelProperty(value = "城市名称")
    private String name;

    @ApiModelProperty(value = "省code")
    private Integer provinceCode;
}
