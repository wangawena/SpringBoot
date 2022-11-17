package com.fl.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fl.pojo.Result;
import com.fl.pojo.User;
import com.fl.server.UserServer;
import com.fl.util.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @PostMapping("/login")
    //@GetMapping("/login")
    public Result login(User user)
    {
        JWTutil jwTutil=new JWTutil();

        if(user.getAccount()==null || user.getPassword()==null)
        {
            Result result=new Result();
            result.setMsg("未填写账号或密码！");
            result.setData("");
            result.setSuccess("false");
            return  result;
        }

        Result result=userServer.login(user);
        HttpSession session= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest().getSession();

        User u=(User)result.getData();
        if(u.getAccount()!=null && u.getPassword()!=null)
        {
            session.setAttribute("account",u.getAccount());
            session.setAttribute("password",u.getPassword());
            System.out.println(session.getAttribute("account"));
            System.out.println(session.getAttribute("password"));
        }

        String randomString= jwTutil.randomString();
        String token=jwTutil.getToken(u.getAccount(),randomString,String.valueOf(System.currentTimeMillis()));

        result.setSuccess("success");
        result.setToken(token);
        
        return result;
    }
}


