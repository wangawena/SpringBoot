package com.fl.server;

import com.fl.pojo.Result;
import com.fl.pojo.User;

public interface UserServer {

    public Result login(User user);
}
