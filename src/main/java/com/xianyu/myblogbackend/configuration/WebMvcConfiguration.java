package com.xianyu.myblogbackend.configuration;


import com.xianyu.myblogbackend.interceptor.JwtAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtAdminInterceptor jwtAdminInterceptor;

    //这里注册拦截器
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        log.info("后台拦截器");
        //管理员拦截器
        registry.addInterceptor(jwtAdminInterceptor)
                .addPathPatterns("/admin/**");

        //todo：添加一个用户功能拦截器，拦截路径为用户的特定功能
        //todo: 写一个登录接口，登录之后向header中添加token
        super.addInterceptors(registry);
    }
}
