package com.example.books_management.service;

import com.example.books_management.request.AddBorrowBookRequest;
import com.example.books_management.request.updateBorrowBookTermRequest;
import com.example.books_management.response.BorrowBooksResponse;

import java.util.List;

public interface BorrowBooksService {

    Integer insertBorrowBooks(Long userId,AddBorrowBookRequest addBorrowBookRequest);

    Integer deleteBorrowBooks(Long id);

    Integer updateBorrowBooks(updateBorrowBookTermRequest updateBorrowBookTermRequest);

    List<BorrowBooksResponse> selectAllBorrowBooks(Integer pageNum);

    BorrowBooksResponse selectOneBorrowBooksById(Long id);

    List<BorrowBooksResponse> selectBorrowBooksByUserId(Long userId,Integer pageNum);

    List<BorrowBooksResponse> selectBorrowBooksByBookId(Long bookId,Integer pageNum);
}
