//该拦截器只会拦截需要登录之后才能的功能：评论等等
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
public class JwtUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    private final String USER_ID = "userId";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if(!(handler instanceof HandlerMethod)){
            //不为动态方法直接放行
            return true;
        }

        String token = request.getHeader(jwtProperties.getUserTokenName());

        try{
            log.info("用户校验");
            Claims claims = JwtUtils.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(USER_ID).toString());
            BaseContext.setCurrentId(userId);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}
