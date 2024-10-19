package com.xianyu.myblogbackend.controller.admin;


import com.xianyu.myblogbackend.model.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TestController {

    @GetMapping("/test")
    public Result test(){
        return Result.success();
    }
}
