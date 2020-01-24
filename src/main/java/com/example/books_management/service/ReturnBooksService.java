package com.example.books_management.service;

import com.example.books_management.response.ReturnBooksResponse;

import java.util.List;

public interface ReturnBooksService {

    Integer insertReturnBooks(Long borrowBooksId);

    Integer deleteReturnBooks(Long id);

    List<ReturnBooksResponse> selectAllReturnBooks(Integer pageNum);

    ReturnBooksResponse selectReturnBooksById(Long id);

    List<ReturnBooksResponse> selectReturnBooksByUserId(Long userId,Integer pageNum);

    List<ReturnBooksResponse> selectReturnBooksByBookId(Long bookId,Integer pageNum);
}
