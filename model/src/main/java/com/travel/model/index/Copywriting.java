package com.travel.model.index;

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
@ApiModel(description = "首页文案")
@TableName("copywriting")
public class Copywriting extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文案标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "文案正文")
    @TableField("text")
    private String text;

    @ApiModelProperty(value = "文案注解")
    @TableField("note")
    private String note;
}
