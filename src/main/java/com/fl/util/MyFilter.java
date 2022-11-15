package com.fl.util;


import com.fl.pojo.FilterTime;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        HttpSession session= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getSession();

        String URI=((HttpServletRequest)servletRequest).getRequestURI();
        System.out.println("URI:"+URI);

        if("/user/login".compareTo(URI)!=0)
        {
            String account = session.getAttribute("account").toString();
            String password = session.getAttribute("password").toString();

            System.out.println("account:" + account + "\n" + "password:" + password);
        }
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
