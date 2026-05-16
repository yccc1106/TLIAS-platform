package org.ycc.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//拦截所有请求
@Slf4j
public class DemoFilter implements Filter {
    //初始化方法
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化过滤器....");
    }

    //拦截到请求后，执行，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter 拦截到请求，执行.... 放行前");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("doFilter 拦截到请求，执行.... 放行后");
    }

    //销毁方法
    @Override
    public void destroy() {
        log.info("destroy 销毁方法....");
    }
}
