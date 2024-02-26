package com.travel.service;

import com.travel.result.Result;

/**
 *
 * @author Build_start
 */
public interface MailService {

    /**
     * 发送邮箱验证码
     * @param email 邮箱
     * @return
     */
    public Result sendEmailCode(String email);

}
