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
@TableName("user_authority")
public class UserAuthority extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "权限id")
    @TableField("authority_id")
    private Long authorityId;
}
