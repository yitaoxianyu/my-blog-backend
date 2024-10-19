package com.xianyu.myblogbackend.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;

    private String userName;

    private String password;

    private int status;

    private String email;

    private LocalDateTime loginTime;

    private String role;
}
