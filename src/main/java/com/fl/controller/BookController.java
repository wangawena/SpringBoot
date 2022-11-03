package com.fl.controller;

import com.fl.pojo.Book;
import com.fl.server.DaoServerImpl;
import com.fl.server.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("DaoService1")
    private DaoService daoServer;

    @GetMapping("/findAll")
    public List<Book> findAll()
    {
        List<Book> bookList=daoServer.findAll();
        return  bookList;

    }

    @GetMapping("/findByName/{name}")
    public List<Book> findByName(@PathVariable String name)
    {
        List<Book> bookList=new ArrayList<Book>();
        bookList=daoServer.findByName(name);
        return bookList;
    }


    @GetMapping("/delectById/{id}")
    public String delectById(@PathVariable int id)
    {
        Book book=daoServer.findById(id);
        if(book!=null) {
            System.out.println(book.toString());
            daoServer.delectById(id);
            return "删除成功";
        }
        return "错误！没有该对象";
    }



    @PostMapping("/addBook")
    public String addBook(Book book)
    {
        daoServer.addBood(book);
        return "success";
    }


    @GetMapping("/findBookByAuthor")
    public List<Book> findBookByAuthor(String author)
    {
        List<Book> bookList=new ArrayList<>();
        bookList=daoServer.findBookByAuthor(author);
        return bookList;
    }
}
