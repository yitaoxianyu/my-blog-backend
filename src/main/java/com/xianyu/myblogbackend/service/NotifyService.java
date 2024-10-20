package com.xianyu.myblogbackend.service;

import com.xianyu.myblogbackend.model.dto.LoginDTO;
import com.xianyu.myblogbackend.model.dto.RegisterDTO;

public interface NotifyService {

    void login(LoginDTO loginDTO);

    void register(RegisterDTO registerDTO);

    void sendCaptcha(String email);
}
