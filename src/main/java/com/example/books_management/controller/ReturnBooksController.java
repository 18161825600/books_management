package com.example.books_management.controller;

import com.example.books_management.response.ReturnBooksResponse;
import com.example.books_management.service.ReturnBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReturnBooksController {

    @Autowired
    private ReturnBooksService returnBooksService;

    @PostMapping(value = "insert/return/book")
    public Integer insertReturnBooks(Long borrowBooksId){
        return returnBooksService.insertReturnBooks(borrowBooksId);
    }

    @DeleteMapping(value = "delete/return/book")
    public Integer deleteReturnBooks(Long id){
        return returnBooksService.deleteReturnBooks(id);
    }

    @GetMapping(value = "select/all/return/book")
    public List<ReturnBooksResponse> selectAllReturnBooks(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return returnBooksService.selectAllReturnBooks(pageNum);
    }

    @GetMapping(value = "select/one/return/book")
    public ReturnBooksResponse selectReturnBooksById(Long id){
        return returnBooksService.selectReturnBooksById(id);
    }

    @GetMapping(value = "select/return/book/userId")
    public List<ReturnBooksResponse> selectReturnBooksByUserId(Long userId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return returnBooksService.selectReturnBooksByUserId(userId, pageNum);
    }

    @GetMapping(value = "select/return/book/bookId")
    public List<ReturnBooksResponse> selectReturnBooksByBookId(Long bookId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return returnBooksService.selectReturnBooksByBookId(bookId, pageNum);
    }
}
