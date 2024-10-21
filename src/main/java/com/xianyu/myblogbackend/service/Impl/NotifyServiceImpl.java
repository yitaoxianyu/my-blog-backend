package com.xianyu.myblogbackend.service.Impl;


import com.xianyu.myblogbackend.mapper.UserMapper;
import com.xianyu.myblogbackend.model.dto.CaptchaDTO;
import com.xianyu.myblogbackend.model.dto.LoginDTO;
import com.xianyu.myblogbackend.model.dto.RegisterDTO;
import com.xianyu.myblogbackend.model.entity.User;
import com.xianyu.myblogbackend.model.properties.JwtProperties;
import com.xianyu.myblogbackend.model.utils.JwtUtils;
import com.xianyu.myblogbackend.model.vo.LoginVO;
import com.xianyu.myblogbackend.service.NotifyService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class NotifyServiceImpl implements NotifyService {

    private final String role = "user";
    private final String message = "您的验证码为:";
    private final String from = "2861587619@qq.com";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    public LoginVO login(LoginDTO loginDTO) throws Exception {
        //md5解密之后和数据库中的密码对比，之后生成jwTtoken返回给前端存储，同时设置id
        //根据username查用户
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userMapper.getByUsername(username);
        if(user == null) throw new Exception("用户不存在");


        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) throw new Exception("密码错误");

        if(user.getStatus() != 1) throw new Exception("用户被禁用");
        //此时登录成功，返回一个jwttoken
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        String token = JwtUtils.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), map);
        String role = user.getRole();

        LoginVO loginVO = LoginVO.builder().token(token).username(username).role(role).build();

        return loginVO;
    }

    public void register(RegisterDTO registerDTO) throws Exception {
        //校验验证码
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String captcha = (String) valueOperations.get(registerDTO.getUsername());

        if(captcha == null || !captcha.equals(registerDTO.getCaptcha())) throw new Exception("验证码不正确");

        //创建用户,应该将密码进行md5加密再存入数据库。
        String password = registerDTO.getPassword();
        String passwordAsHex = DigestUtils.md5DigestAsHex(password.getBytes());

        //默认状态为1
        User user = new User();
        user.setUserName(registerDTO.getUsername());
        user.setPassword(passwordAsHex);
        user.setLoginTime(LocalDateTime.now());
        user.setRole(role);
        user.setStatus(1);

        userMapper.insert(user);
        return ;
        }

    public void sendCaptcha(CaptchaDTO captchaDTO) throws Exception {
        if (captchaDTO.getUsername() == null || captchaDTO.getUsername().isEmpty()) {
            throw new Exception("用户名不能为空");
        }

        //构造验证码
        Random random = new Random();
        String captcha = String.valueOf(random.nextInt(1000000)).substring(0,6);


        SimpleMailMessage msg = new SimpleMailMessage();
        //发送短信
        msg.setTo(captchaDTO.getEmail());
        msg.setFrom(from);
        msg.setSubject("验证码");
        msg.setText(message + captcha);
        try{
            mailSender.send(msg);
        }catch (MailSendException e){
            throw new Exception("邮件发送失败:" + e.getMessage(),e);
        }

        String key = captchaDTO.getUsername();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //String类型存入
        valueOperations.set(key,captcha,2, TimeUnit.MINUTES);
    }
}


