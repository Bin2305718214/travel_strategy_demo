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
@ApiModel(description = "电话信息")
@TableName("tel_info")
public class TelInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "实用信息id")
    @TableField("info_id")
    private Long infoId;

    @ApiModelProperty(value = "电话名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone")
    private String phone;
}
