package com.fl.server;

import com.fl.pojo.Author;
import com.fl.pojo.Book;

import java.util.List;

public interface BookService {

    public List<Book> findAll();

    public List<Book> findByName(String name);

    public void delectById(int id);

    public Book findById(int id);

    public int addBood(Book book);

    public List<Book> findBookByAuthor(String author);

    public List<Author> findAuthorInformationByBook(String bookName);
}
