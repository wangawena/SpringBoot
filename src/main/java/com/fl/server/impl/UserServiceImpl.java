package com.fl.server.impl;

import com.fl.dao.UserDao;
import com.fl.pojo.Result;
import com.fl.pojo.User;
import com.fl.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserServer {

    @Autowired
    UserDao userDao;

    @Override
    public Result login(User user) {
        Result result=new Result();
        result.setSuccess("false");
        result.setData(null);

        int exist=userDao.login(user);
        if(exist==0)
            result.setMsg("账户名或密码错误！");
        else
        {
            result.setMsg("登录成功！");
            result.setSuccess("success");
            result.setData(user);
        }
        return result;
    }
}
