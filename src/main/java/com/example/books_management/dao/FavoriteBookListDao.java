package com.example.books_management.dao;

import com.example.books_management.mapper.FavoriteBookListMapper;
import com.example.books_management.pojo.FavoriteBookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class FavoriteBookListDao {

    @Autowired
    private FavoriteBookListMapper favoriteBookListMapper;

    public Integer insertFavoriteBook(FavoriteBookList favoriteBookList){
        return favoriteBookListMapper.insert(favoriteBookList);
    }

    public Integer deleteFavoriteBook(Long id){
        return favoriteBookListMapper.deleteByPrimaryKey(id);
    }

    public List<FavoriteBookList> selectAllFavoriteBookList(){
        return favoriteBookListMapper.selectAll();
    }

    public FavoriteBookList selectFavoriteBookById(Long id){
        return favoriteBookListMapper.selectByPrimaryKey(id);
    }

    public List<FavoriteBookList> selectFavoriteByUserId(Long userId){
        Example example = new Example(FavoriteBookList.class);
        example.createCriteria().andEqualTo("userId",userId);
        return favoriteBookListMapper.selectByExample(example);
    }

    public List<FavoriteBookList> selectFavoriteByBookId(Long bookId){
        Example example = new Example(FavoriteBookList.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return favoriteBookListMapper.selectByExample(example);
    }

    public Integer countAllFavoriteBook(){
        Example example = new Example(FavoriteBookList.class);
        return favoriteBookListMapper.selectCountByExample(example);
    }

    public Integer countFavoriteBookByUserId(Long userId){
        Example example = new Example(FavoriteBookList.class);
        example.createCriteria().andEqualTo("userId",userId);
        return favoriteBookListMapper.selectCountByExample(example);
    }

    public Integer countFavoriteBookByBookId(Long bookId){
        Example example = new Example(FavoriteBookList.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return favoriteBookListMapper.selectCountByExample(example);
    }
}
