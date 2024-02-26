package com.travel.model.city;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.travel.model.base.BaseCityInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Build_start
 * @create 2022-12-18 13:57
 */

@Data
@ApiModel(description = "城市购物点")
@TableName("shopping")
public class Shopping extends BaseCityInfo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物点地址")
    @TableField("address")
    private String address;
}
