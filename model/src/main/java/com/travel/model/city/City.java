package com.travel.model.city;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-16 16:47
 */

@Data
@ApiModel(description = "城市信息")
@TableName("city")
public class City {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "拼音")
    @TableField("pinyin")
    private String pinyin;

    @ApiModelProperty(value = "介绍")
    @TableField("intro")
    private String intro;

    @ApiModelProperty(value = "封面图片1")
    @TableField("cover_img1")
    private String coverImg1;

    @ApiModelProperty(value = "封面图片2")
    @TableField("cover_img2")
    private String coverImg2;

    @ApiModelProperty(value = "推荐时间")
    @TableField("recomm_time")
    private String recommTime;

    @ApiModelProperty(value = "阅览量")
    @TableField("reading_num")
    private Integer readingNum;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
