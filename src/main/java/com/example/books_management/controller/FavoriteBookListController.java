package com.example.books_management.controller;

import com.example.books_management.response.FavoriteBookListResponse;
import com.example.books_management.service.FavoriteBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteBookListController {

    @Autowired
    private FavoriteBookListService favoriteBookListService;

    @PostMapping(value = "insert/favorite/book")
    public Integer insertFavoriteBook(Long userId,Long bookId){
        return favoriteBookListService.insertFavoriteBook(userId, bookId);
    }

    @DeleteMapping(value = "delete/favorite/book")
    public Integer deleteFavoriteBook(Long id){
        return favoriteBookListService.deleteFavoriteBook(id);
    }

    @DeleteMapping(value = "delete/some/borrow/book")
    public Integer deleteSomeFavoriteBook(@RequestBody List<Long> ids){
        return favoriteBookListService.deleteSomeFavoriteBook(ids);
    }

    @GetMapping(value = "select/all/favoite/book")
    public List<FavoriteBookListResponse> selectAllFavoriteBookList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return favoriteBookListService.selectAllFavoriteBookList(pageNum);
    }

    @GetMapping(value = "select/one/favorite/book")
    public FavoriteBookListResponse selectFavoriteBookById(Long id){
        return favoriteBookListService.selectFavoriteBookById(id);
    }

    @GetMapping(value = "select/favorite/book/userId")
    public List<FavoriteBookListResponse> selectFavoriteByUserId(Long userId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return favoriteBookListService.selectFavoriteByUserId(userId, pageNum);
    }

    @GetMapping(value = "select/favorite/book/bookId")
    public List<FavoriteBookListResponse> selectFavoriteByBookId(Long bookId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return favoriteBookListService.selectFavoriteByBookId(bookId, pageNum);
    }
}
