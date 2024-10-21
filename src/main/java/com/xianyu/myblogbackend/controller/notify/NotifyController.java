package com.xianyu.myblogbackend.controller.notify;


import com.xianyu.myblogbackend.model.dto.CaptchaDTO;
import com.xianyu.myblogbackend.model.dto.LoginDTO;
import com.xianyu.myblogbackend.model.dto.RegisterDTO;
import com.xianyu.myblogbackend.model.result.Result;
import com.xianyu.myblogbackend.model.vo.LoginVO;
import com.xianyu.myblogbackend.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) throws Exception {

        LoginVO loginVO = notifyService.login(loginDTO);
        return Result.success(loginVO);
    }

    @PostMapping("/captcha")
    public Result sendCaptcha(@RequestBody CaptchaDTO captchaDTO) throws Exception {

        notifyService.sendCaptcha(captchaDTO);
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO) throws Exception {

        notifyService.register(registerDTO);
        return Result.success();
    }

}
