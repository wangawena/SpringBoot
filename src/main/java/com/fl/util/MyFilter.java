package com.fl.util;


import com.fl.pojo.FilterTime;
import io.jsonwebtoken.Jwts;
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

    //需要过滤的路径
    String[] filterURL={"/book/findAll",
            "/author/*"};

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

        //获取访问路径
        String URL=((HttpServletRequest)servletRequest).getRequestURI();
        System.out.println("URL:"+URL);

        System.out.println("访问路径:"+isURL(URL));
        if(isURL(URL))
        {
            //获取token
            String token=((HttpServletRequest)servletRequest).getHeader("Authorization");
            System.out.println("Tokne:"+token);
            /*
            String account = session.getAttribute("account").toString();//账号
            String password = session.getAttribute("password").toString();//密码
            System.out.println("account:" + account + "\n" + "password:" + password);
            */

            filterTime.setEndTime(System.currentTimeMillis());
            System.out.println("过滤器执行时间："+filterTime.getSubTime());

            JWTutil jwTutil=new JWTutil();
            if(token!=null && jwTutil.isVerfify(token))
                filterChain.doFilter(servletRequest,servletResponse);
            else
            {
                HttpServletRequest request=(HttpServletRequest)servletRequest;
                String requestURI="/unsuccess";
                servletRequest.getRequestDispatcher(requestURI).forward(servletRequest,servletResponse);
            }
        }



        else {
            filterChain.doFilter(servletRequest, servletResponse);
            filterTime.setEndTime(System.currentTimeMillis());
            System.out.println("过滤器执行时间：" + filterTime.getSubTime());
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁过滤器");
    }


    //判断该路径是否需要过滤
    public boolean isURL(String URL)
    {
        for(String s: filterURL)
        {
            //需要过滤
            if(s.compareTo(URL)==0)
                return true;
        }
        return false;
    }
}
