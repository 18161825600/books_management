package com.example.books_management.service;

import com.example.books_management.response.FavoriteBookListResponse;

import java.util.List;

public interface FavoriteBookListService {

    Integer insertFavoriteBook(Long userId,Long bookId);

    Integer deleteFavoriteBook(Long id);

    List<FavoriteBookListResponse> selectAllFavoriteBookList(Integer pageNum);

    FavoriteBookListResponse selectFavoriteBookById(Long id);

    List<FavoriteBookListResponse> selectFavoriteByUserId(Long userId,Integer pageNum);

    List<FavoriteBookListResponse> selectFavoriteByBookId(Long bookId,Integer pageNum);
}
