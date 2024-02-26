package com.travel.model.city;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.travel.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Build_start
 * @create 2022-12-18 13:57
 */

@Data
@ApiModel(description = "实用信息")
@TableName("practical_info")
public class Practical extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "城市id")
    @TableField("city_id")
    private Long cityId;

    @ApiModelProperty(value = "信息名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "信息内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "子节点")
    @TableField(exist = false)
    private List child = new ArrayList<>();
}
