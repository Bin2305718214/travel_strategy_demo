package com.travel.service.impl;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * 邮箱服务
 * @author Build_start
 */
@Service
public class MsnService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单的邮箱
     *
     * @param to 收件人
     * @param theme 标题
     * @param content 正文内容
     * @param cc 抄送
     */
    public void sendSimpleMail(String to, String theme, String content, String... cc) {
        // 创建邮件对象
        SimpleMailMessage message = new SimpleMailMessage();

        try {
            message.setFrom(String.valueOf(new InternetAddress(from, "天南地北", "UTF-8")));      // 发件人
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        message.setTo(to);          // 收件人
        message.setSubject(theme);  // 标题
        message.setText(content);   // 内容

        if (ArrayUtils.isNotEmpty(cc)) {
            message.setCc(cc);
        }

        // 发送
        javaMailSender.send(message);
    }
}
