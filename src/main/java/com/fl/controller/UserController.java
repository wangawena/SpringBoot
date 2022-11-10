package com.fl.controller;


import com.fl.pojo.Result;
import com.fl.pojo.User;
import com.fl.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @PostMapping("/login")
    public Result login(User user)
    {
        Result result=userServer.login(user);
        HttpSession session= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest().getSession();

        User u=(User)result.getData();
        //if(!"".equals(u))

        return result;
    }


}
