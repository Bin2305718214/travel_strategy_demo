package com.travel.service.impl;

import com.travel.result.Result;
import com.travel.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Build_start
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ThreadService threadService;

    @Override
    public Result sendEmailCode(String email) {
        // 随机生成6位数字验证码
        String code = String.valueOf(new Random().nextInt(899999) + 100000);

        // 正文内容
        String content = "亲爱的用户：\n" +
                "您此次的验证码为：\n\n" +
                code + "\n\n" +
                "此验证码5分钟内有效，请立即进行下一步操作。 如非你本人操作，请忽略此邮件。\n" +
                "感谢您的使用！";

        // 发送验证码
        threadService.sendSimpleMail(email, "您此次的验证码为：" + code, content);
        // 丢入缓存，设置5分钟过期
        redisTemplate.opsForValue().set("EMAIL_" + email, code, 5 * 60, TimeUnit.SECONDS);

        return Result.ok();
    }
}
