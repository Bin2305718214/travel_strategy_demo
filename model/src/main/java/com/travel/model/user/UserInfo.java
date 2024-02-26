package com.travel.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于前端的用户信息显示
 * @author Build_start
 */
@Data
public class UserInfo {

    private Long id;

    private String email;

    private String nickName;

    private String avatarUrl;

    private String autograph;

    private Integer status;

    private String role;

}
