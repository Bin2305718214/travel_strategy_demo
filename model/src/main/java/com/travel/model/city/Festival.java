package com.travel.model.city;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.travel.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Build_start
 * @create 2022-12-18 13:57
 */

@Data
@ApiModel(description = "城市节日")
@TableName("festival")
public class Festival extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "城市id")
    @TableField("city_id")
    private Long cityId;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "拼音")
    @TableField("pinyin")
    private String pinyin;

    @ApiModelProperty(value = "介绍")
    @TableField("intro")
    private String intro;

    @ApiModelProperty(value = "举办地点")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "举办日期")
    @TableField("date")
    private String date;
}
