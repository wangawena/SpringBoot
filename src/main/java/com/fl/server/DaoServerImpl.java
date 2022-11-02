package com.fl.server;

import com.fl.dao.BookDao;
import com.fl.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DaoService1")
public class DaoServerImpl implements DaoService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }


    public List<Book> findByName(String name)
    {
        return  bookDao.findByName(name);
    }

    @Override
    public void delectById(int id) {
        bookDao.delectById(id);
    }

    @Override
    public Book findById(int id) {

        return bookDao.findById(id);
    }
}
