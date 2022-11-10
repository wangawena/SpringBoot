package com.fl.controller;

import com.fl.pojo.Author;
import com.fl.server.AuthorServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    @Qualifier("AuthorServer")
    private AuthorServer authorServer;

    @GetMapping("/findAllAuthor")
    public List<Author> findAllAuthor() {
        return authorServer.findAllAuthor();
    }

    @GetMapping("/findAuthorByName")
    public List<Author> findAuthorByName(String name) {
        return authorServer.findAuthorByName(name);
    }

    @GetMapping("/findAuthorByCountry")
    public List<Author> findAuthorByCountry(String country) {
        return authorServer.findAuthorByCountry(country);
    }


    public String deletcByName(String name)
    {
        return authorServer.deletcByName(name);
    }
}
