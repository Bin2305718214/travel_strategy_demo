package com.travel.model.user;

import lombok.Data;

/**
 * 用于用户修改密码
 * @author Build_start
 */
@Data
public class UserPwd {

    private Long id;

    private String oldPassword;

    private String newPassword;

}
