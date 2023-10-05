package com.husky.hqMovie.config;

import com.husky.hqMovie.interceptor.LoginInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor=new LoginInterceptor();
        List<String> list=new ArrayList<>();
        list.add("/css/**");
        list.add("/js/**");
        list.add("/index.html");
        list.add("/web/register.html");
        list.add("/web/login.html");
        list.add("/user/login");
        list.add("/user/register");
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(list);
    }
}
