package com.travel.model.discuss;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.travel.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Build_start
 */
@Data
@ApiModel(description = "讨论帖子")
@TableName("post")
public class Post extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "封面图片")
    @TableField("cover_img")
    private String coverImg;

    @ApiModelProperty(value = "帖子标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "帖子内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "帖子图片")
    @TableField("post_imgs")
    private String postImgs;

    @ApiModelProperty(value = "点赞量")
    @TableField("praise_num")
    private Integer praiseNum;

    @ApiModelProperty(value = "评论量")
    @TableField("comments_num")
    private Integer commentsNum;

    @ApiModelProperty(value = "阅览量")
    @TableField("reading_num")
    private Integer readingNum;

    @ApiModelProperty("是否点赞")
    @TableField(exist = false)
    private Boolean isPraise = false;

}
