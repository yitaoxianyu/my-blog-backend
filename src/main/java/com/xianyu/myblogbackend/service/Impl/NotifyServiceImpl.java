package com.xianyu.myblogbackend.service.Impl;


import com.xianyu.myblogbackend.mapper.UserMapper;
import com.xianyu.myblogbackend.model.dto.LoginDTO;
import com.xianyu.myblogbackend.model.dto.RegisterDTO;
import com.xianyu.myblogbackend.model.entity.User;
import com.xianyu.myblogbackend.service.NotifyService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class NotifyServiceImpl implements NotifyService {

    private final String role = "user";
    private final String message = "您的验证码为:";
    private final String from = "2861587619@qq.com";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailSender mailSender;

    public void login(LoginDTO loginDTO) {

    }

    public void register(RegisterDTO registerDTO) {
        //创建用户,应该将密码进行md5加密再存入数据库。
        String password = registerDTO.getPassword();
        String passwordAsHex = DigestUtils.md5DigestAsHex(password.getBytes());

        //默认状态为1
        User user = new User();
        user.setUserName(registerDTO.getUsername());
        user.setPassword(passwordAsHex);
        user.setLoginTime(LocalDateTime.now());
        user.setRole(role);

        userMapper.insert(user);
        return ;
    }

    public void sendCaptcha(String email) {
        //构造验证码
        Random random = new Random();
        int captcha = random.nextInt(1000000);


        SimpleMailMessage msg = new SimpleMailMessage();
        //发送短信
        msg.setTo(email);
        msg.setFrom(from);
        msg.setSubject("验证码");
        msg.setText(message + captcha);
        mailSender.send(msg);
        System.out.println("邮件发送成功");
    }
}
