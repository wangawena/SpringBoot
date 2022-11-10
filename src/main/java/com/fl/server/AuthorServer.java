package com.fl.server;

import com.fl.pojo.Author;

import java.util.List;

public interface AuthorServer {

    public List<Author> findAllAuthor();

    public List<Author> findAuthorByName(String name);

    public List<Author> findAuthorByCountry(String country);

    public String deletcByName(String name);
}
