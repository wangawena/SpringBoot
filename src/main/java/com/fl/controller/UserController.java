package com.fl.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fl.pojo.Result;
import com.fl.pojo.User;
import com.fl.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
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

    //密钥
    private String jwtSecret="XDD6897!&@H.,?";
    //@PostMapping("/login")
    @GetMapping("/login")
    public Result login(User user)
    {
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
        //if(!"".equals(u))

        System.out.println(getToken(u.getAccount(),randomString(), String.valueOf(System.currentTimeMillis()));
        return result;
    }


    public String randomString()
    {
        String[] s={"1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F","G","H","I","J","K","L","M",
                "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int size=s.length;
        String string="";
        Random random=new Random();
        for(int i=0;i<8;i++)
        {
            string+=s[random.nextInt(size)];
        }
        return string;
    }


    public String getToken(String account,String randomString,String time)
    {
        return JWT.create()
                .withClaim("account",account)
                .withClaim("randomString",randomString)
                .withClaim("expireAt",time+3000)
                .sign(Algorithm.HMAC256(jwtSecret));
    }
}


