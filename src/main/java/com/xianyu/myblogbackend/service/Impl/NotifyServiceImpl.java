package com.xianyu.myblogbackend.service.Impl;


import com.xianyu.myblogbackend.mapper.UserMapper;
import com.xianyu.myblogbackend.model.dto.LoginDTO;
import com.xianyu.myblogbackend.model.dto.RegisterDTO;
import com.xianyu.myblogbackend.model.entity.User;
import com.xianyu.myblogbackend.service.NotifyService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotifyServiceImpl implements NotifyService {

    private final String role = "user";

    @Autowired
    private UserMapper userMapper;

    public void login(LoginDTO loginDTO) {

    }

    public void register(RegisterDTO registerDTO) {
        //创建用户
        User user = User.builder()
                .password(registerDTO.getPassword())
                .userName(registerDTO.getUsername())
                .loginTime(LocalDateTime.now())
                .role(role)
                .build();

        userMapper.insert(user);
        return ;
    }
}
