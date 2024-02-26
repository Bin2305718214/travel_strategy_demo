package com.travel.model.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 城市信息详情基类
 * @author Build_start
 */
@Data
public class BaseCityInfo extends BaseEntity{

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

    @ApiModelProperty(value = "封面图片")
    @TableField("cover_img")
    private String coverImg;

}
