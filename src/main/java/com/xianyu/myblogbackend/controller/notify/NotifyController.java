package com.xianyu.myblogbackend.controller.notify;


import com.xianyu.myblogbackend.model.dto.LoginDTO;
import com.xianyu.myblogbackend.model.dto.RegisterDTO;
import com.xianyu.myblogbackend.model.result.Result;
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
    public Result login(@RequestBody LoginDTO loginDTO){

        notifyService.login(loginDTO);
        return Result.success();
    }

    @PostMapping("/captcha/{email}")
    public Result sendCaptcha(@PathVariable String email){

        notifyService.sendCaptcha(email);
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO){

        notifyService.register(registerDTO);
        return Result.success();
    }

}
