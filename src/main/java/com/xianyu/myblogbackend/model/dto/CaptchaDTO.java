package com.xianyu.myblogbackend.model.dto;


import lombok.Data;

@Data
public class CaptchaDTO {
    private String email;

    private String username;
}
