package com.fl.dao;

import com.fl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {


    public int login(User user);

    public int selectByAccount(String Account);
}
