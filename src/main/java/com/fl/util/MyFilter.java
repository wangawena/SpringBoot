package com.fl.util;


import com.fl.pojo.FilterTime;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName ="firstFilter",urlPatterns ={"/*"})
public class MyFilter implements Filter {

    FilterTime filterTime=new FilterTime();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("过滤器初始化");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterTime.setStartTime(System.currentTimeMillis());
        System.out.println("进行过滤");
        filterChain.doFilter(servletRequest,servletResponse);
        filterTime.setEndTime(System.currentTimeMillis());
        System.out.println("过滤器执行时间："+filterTime.getSubTime());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁过滤器");
    }
}
