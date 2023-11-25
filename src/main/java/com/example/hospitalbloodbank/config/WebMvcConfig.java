package com.example.hospitalbloodbank.config;

import com.example.hospitalbloodbank.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/api/**") // 设置拦截路径
                .excludePathPatterns("/api/users/login") // 设置不拦截路径，这里假设登录页面路径为/login
                .excludePathPatterns("/api/users/createUser");
    }
}
