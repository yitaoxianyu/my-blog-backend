package com.xianyu.myblogbackend.model.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myblog.jwt")
@Data
public class JwtProperties {

    private String adminSecretKey;

    private Long adminTtl;

    private String adminTokenName;

    private String userSerretKey;

    private Long userTtl;

    private String userTokenName;
}
