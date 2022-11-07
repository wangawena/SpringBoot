package com.fl.dao;


import com.fl.pojo.Author;
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
    public int addBook(Book book);

    //根据作者查找书籍
    public List<Book> findBookByAuthor(String author);


    //查询书籍作者名字
    public List<String> findAuthorByBook(String bookName);

    //通过作者名查找作者信息
    public List<Author> findInformationByName(List<String> name);
}
