package com.travel.model.discuss;

/**
 * @author Build_start
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.travel.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户评论")
@TableName("post_comments")
public class PostComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "帖子id")
    @TableField("post_id")
    private Long postId;

    @ApiModelProperty(value = "父级id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "用户名称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "回复的用户名")
    @TableField(exist = false)
    private String parentUserName;

}
