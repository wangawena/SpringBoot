package com.fl.dao;


import com.fl.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookDao {

    //查询所有书籍
    public List<Book> findAll();

    //通过名字查询书籍
    public List<Book> findByName(String name);

    //通过ID删除书籍
    public void delectById(int id);

    //通过ID查询书籍
    public Book findById(int id);

    //新增书籍
    public void addBook(Book book);
}
