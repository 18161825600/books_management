package com.example.books_management.mapper;


import com.example.books_management.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends CommonMapper<Book> {
}