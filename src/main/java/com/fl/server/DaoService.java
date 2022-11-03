package com.fl.server;

import com.fl.pojo.Book;

import java.util.List;

public interface DaoService {

    public List<Book> findAll();

    public List<Book> findByName(String name);

    public void delectById(int id);

    public Book findById(int id);

    public void addBood(Book book);
}
