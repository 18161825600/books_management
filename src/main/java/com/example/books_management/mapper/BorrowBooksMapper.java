package com.example.books_management.mapper;


import com.example.books_management.pojo.BorrowBooks;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowBooksMapper extends CommonMapper<BorrowBooks> {
}