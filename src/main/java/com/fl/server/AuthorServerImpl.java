package com.fl.server;

import com.fl.dao.AuthorDao;
import com.fl.pojo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AuthorServer")
public class AuthorServerImpl implements AuthorServer{

    @Autowired
    private AuthorDao authorDao;

    @Override
    public List<Author> findAllAuthor() {
        return authorDao.findAllAuthor();
    }

    @Override
    public List<Author> findAuthorByName(String name) {
        return authorDao.findAuthorByName(name);
    }

    @Override
    public List<Author> findAuthorByCountry(String country) {
        return authorDao.findAuthorByCountry(country);
    }
}
