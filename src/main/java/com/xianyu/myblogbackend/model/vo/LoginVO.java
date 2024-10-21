package com.xianyu.myblogbackend.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {

    private String token;

    private String username;

    //代表身份，返回给前端用于确认跳转到哪一个页面。
    private String role;

}
