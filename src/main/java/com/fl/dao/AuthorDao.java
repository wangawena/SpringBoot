package com.fl.dao;

import com.fl.pojo.Author;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthorDao {

    public List<Author> findAllAuthor();

    public List<Author> findAuthorByName(String name);

    public List<Author> findAuthorByCountry(String country);


    public int deletcByName(String name);

}
