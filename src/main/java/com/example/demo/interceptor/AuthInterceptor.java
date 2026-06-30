package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * AuthInterceptor 演示拦截器的使用。
 * 作用：在请求进入 Controller 之前进行拦截检查。
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这里只做示例：实际项目中可以校验 Token、会话、权限等。
        String authHeader = request.getHeader("X-Auth-Token");
        if (authHeader == null || authHeader.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("缺少 X-Auth-Token 请求头，请先登录");
            return false;
        }
        return true;
    }
}
