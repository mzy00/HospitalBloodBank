package com.example.hospitalbloodbank.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前请求的session
        HttpSession session = request.getSession();

        // 判断用户是否已登录，这里假设登录成功后将用户信息存储在session中
        Object user = session.getAttribute("user");
        if (user == null) {
            // 用户未登录，重定向到登录页面
            response.sendRedirect("/login"); // 你的登录页面路径
            return false;
        }

        // 用户已登录，允许访问其他功能
        return true;
    }
}
