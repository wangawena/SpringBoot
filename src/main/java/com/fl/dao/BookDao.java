package com.fl.dao;


import com.fl.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookDao {

    public List<Book> findAll();

    public List<Book> findByName(String name);

    public void delectById(int id);

    public Book findById(int id);
}
