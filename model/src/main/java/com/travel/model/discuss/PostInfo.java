package com.travel.model.discuss;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.travel.model.user.User;

import java.util.Date;

/**
 * @author Build_start
 */
public class PostInfo {

    private Post post;

    private User user;

    private PostComment postComment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
