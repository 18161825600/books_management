package com.example.books_management.controller;

import com.example.books_management.request.AddBorrowBookRequest;
import com.example.books_management.request.updateBorrowBookTermRequest;
import com.example.books_management.response.BorrowBooksResponse;
import com.example.books_management.service.BorrowBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowBooksController {

    @Autowired
    private BorrowBooksService borrowBooksService;

    @PostMapping(value = "borrow/book")
    public Integer insertBorrowBooks(Long userId, @RequestBody AddBorrowBookRequest addBorrowBookRequest){
        return borrowBooksService.insertBorrowBooks(userId, addBorrowBookRequest);
    }

    @DeleteMapping(value = "delete/borrow/book")
    public Integer deleteBorrowBooks(Long id){
        return borrowBooksService.deleteBorrowBooks(id);
    }

    @PutMapping(value = "update/borrow/book")
    public Integer updateBorrowBooks(@RequestBody updateBorrowBookTermRequest updateBorrowBookTermRequest){
        return borrowBooksService.updateBorrowBooks(updateBorrowBookTermRequest);
    }

    @GetMapping(value = "select/all/borrow/book")
    public List<BorrowBooksResponse> selectAllBorrowBooks(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        return borrowBooksService.selectAllBorrowBooks(pageNum);
    }

    @GetMapping(value = "select/one/borrow/book")
    public BorrowBooksResponse selectOneBorrowBooksById(Long id){
        return borrowBooksService.selectOneBorrowBooksById(id);
    }

    @GetMapping(value = "select/borrow/book/by/userId")
    public List<BorrowBooksResponse> selectBorrowBooksByUserId(Long userId,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        return borrowBooksService.selectBorrowBooksByUserId(userId, pageNum);
    }

    @GetMapping(value = "select/borrow/book/by/bookId")
    public List<BorrowBooksResponse> selectBorrowBooksByBookId(Long bookId,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        return borrowBooksService.selectBorrowBooksByBookId(bookId, pageNum);
    }
}
