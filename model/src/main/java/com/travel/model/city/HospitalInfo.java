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
@ApiModel(description = "医院信息")
@TableName("hospital_info")
public class HospitalInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "实用信息id")
    @TableField("info_id")
    private Long infoId;

    @ApiModelProperty(value = "医院名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "医院地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone")
    private String phone;
}
