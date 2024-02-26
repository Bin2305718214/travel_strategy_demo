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
@ApiModel(description = "用户浏览记录")
@TableName("browse_record")
public class BrowseRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 访问记录类型：城市
     */
    public static final String CITY = "CITY";

    /**
     * 访问记录类型：帖子
     */
    public static final String POST = "POST";

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "城市id")
    @TableField("browse_id")
    private Long browseId;

    @ApiModelProperty(value = "城市id")
    @TableField("browse_type")
    private String browseType;
}
