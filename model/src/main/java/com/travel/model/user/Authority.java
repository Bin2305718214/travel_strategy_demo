package com.travel.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.travel.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Build_start
 */

@Data
@ApiModel(description = "权限信息")
@TableName("authority")
public class Authority extends BaseEntity {

    @ApiModelProperty(value = "权限")
    @TableField("authority")
    private String authority;

}
