package com.xianyu.myblogbackend;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class MyBlogBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogBackendApplication.class, args);
    }

}
