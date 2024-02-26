package com.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 不占用主线程运行，开辟线程执行邮箱发送操作
 */
@Service
public class ThreadService {

    @Autowired
    private MsnService msnService;

    /**
     * 发送邮箱
     * @param to 收件人
     * @param theme 主题
     * @param content 内容
     */
    @Async("taskExecutor")
    public void sendSimpleMail(String to, String theme, String content) {
        msnService.sendSimpleMail(to, theme, content);
    }
}