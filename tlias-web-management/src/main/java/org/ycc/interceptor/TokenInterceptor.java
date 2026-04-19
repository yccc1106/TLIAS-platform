package org.ycc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.ycc.utils.JwtUtils;

/**
 * 令牌拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.获取请求参数
        String requstURI = request.getRequestURI();

        //2.判断是否是登录请求，如果是登录请求，则放行
        if (requstURI.contains("login")) {
            log.info("登录请求，放行");
            return true;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，如果不存在，则返回401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5.解析token，如果解析失败，则返回401
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6.解析成功，放行
        log.info("令牌解析成功,放行");
        return true;
    }
}
