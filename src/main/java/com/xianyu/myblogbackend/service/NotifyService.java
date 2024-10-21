package com.xianyu.myblogbackend.service;

import com.xianyu.myblogbackend.model.dto.CaptchaDTO;
import com.xianyu.myblogbackend.model.dto.LoginDTO;
import com.xianyu.myblogbackend.model.dto.RegisterDTO;
import com.xianyu.myblogbackend.model.vo.LoginVO;

public interface NotifyService {

    LoginVO login(LoginDTO loginDTO) throws Exception;

    void register(RegisterDTO registerDTO) throws Exception;

    void sendCaptcha(CaptchaDTO captchaDTO) throws Exception;
}
