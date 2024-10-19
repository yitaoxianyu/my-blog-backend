package com.xianyu.myblogbackend.interceptor;

import com.xianyu.myblogbackend.model.BaseContext;
import com.xianyu.myblogbackend.model.properties.JwtProperties;
import com.xianyu.myblogbackend.model.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    private final String ADMIN_ID = "adminId";

    //这里的拦截器主要是用来防止，使用postman直接向后端进行发包这种情况
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        //获取token
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        try{
            log.info("管理员校验");
            Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long adminId = Long.valueOf(claims.get(ADMIN_ID).toString());
            //管理员id
            BaseContext.setCurrentId(adminId);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
