package com.imooc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author peterLee
 * @Date 2019/1/21 4:15 PM
 * @Describtion : 过滤器
 */
@Slf4j
public class requestParamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //request.getSession().getAttribute("user");
        log.info(" do filter url=,{},{}",Thread.currentThread().getId(),request.getContextPath());
        //获取线程的id
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
