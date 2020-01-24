package com.example.books_management.service.impl;

import com.example.books_management.dao.BookDao;
import com.example.books_management.dao.FavoriteBookListDao;
import com.example.books_management.pojo.Book;
import com.example.books_management.pojo.FavoriteBookList;
import com.example.books_management.response.FavoriteBookListResponse;
import com.example.books_management.service.FavoriteBookListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FavoriteBookListServiceImpl implements FavoriteBookListService {

    @Autowired
    private FavoriteBookListDao favoriteBookListDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Integer insertFavoriteBook(Long userId, Long bookId) {
        FavoriteBookList favoriteBookList = new FavoriteBookList();
        favoriteBookList.setUserId(userId);
        favoriteBookList.setBookId(bookId);
        favoriteBookList.setCreateTime(new Date());
        return favoriteBookListDao.insertFavoriteBook(favoriteBookList);
    }

    @Override
    public Integer deleteFavoriteBook(Long id) {
        return favoriteBookListDao.deleteFavoriteBook(id);
    }

    @Override
    public List<FavoriteBookListResponse> selectAllFavoriteBookList(Integer pageNum) {
        List<FavoriteBookList> favoriteBookLists = favoriteBookListDao.selectAllFavoriteBookList();
        PageHelper.startPage(pageNum,10);
        List<FavoriteBookListResponse> list = new ArrayList<>();
        for(FavoriteBookList favoriteBookList : favoriteBookLists){
            FavoriteBookListResponse favoriteBookListResponse = changeFavoriteBookListResponse(favoriteBookList);
            favoriteBookListResponse.setTotal(favoriteBookListDao.countAllFavoriteBook());
            list.add(favoriteBookListResponse);
        }
        PageInfo<FavoriteBookListResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public FavoriteBookListResponse selectFavoriteBookById(Long id) {
        FavoriteBookList favoriteBookList = favoriteBookListDao.selectFavoriteBookById(id);
        return changeFavoriteBookListResponse(favoriteBookList);
    }

    @Override
    public List<FavoriteBookListResponse> selectFavoriteByUserId(Long userId, Integer pageNum) {
        List<FavoriteBookList> favoriteBookLists = favoriteBookListDao.selectFavoriteByUserId(userId);
        PageHelper.startPage(pageNum,10);
        List<FavoriteBookListResponse> list = new ArrayList<>();
        for (FavoriteBookList favoriteBookList : favoriteBookLists){
            FavoriteBookListResponse favoriteBookListResponse = changeFavoriteBookListResponse(favoriteBookList);
            favoriteBookListResponse.setTotal(favoriteBookListDao.countFavoriteBookByUserId(userId));
            list.add(favoriteBookListResponse);
        }
        PageInfo<FavoriteBookListResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<FavoriteBookListResponse> selectFavoriteByBookId(Long bookId, Integer pageNum) {
        List<FavoriteBookList> favoriteBookLists = favoriteBookListDao.selectFavoriteByBookId(bookId);
        PageHelper.startPage(pageNum,10);
        List<FavoriteBookListResponse> list = new ArrayList<>();
        for (FavoriteBookList favoriteBookList : favoriteBookLists){
            FavoriteBookListResponse favoriteBookListResponse = changeFavoriteBookListResponse(favoriteBookList);
            favoriteBookListResponse.setTotal(favoriteBookListDao.countFavoriteBookByBookId(bookId));
            list.add(favoriteBookListResponse);
        }
        PageInfo<FavoriteBookListResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    public FavoriteBookListResponse changeFavoriteBookListResponse(FavoriteBookList favoriteBookList){
        FavoriteBookListResponse favoriteBookListResponse = new FavoriteBookListResponse();
        Book book = bookDao.selectOneById(favoriteBookList.getBookId());
        BeanUtils.copyProperties(book,favoriteBookListResponse);
        favoriteBookListResponse.setCreateTime(favoriteBookList.getCreateTime());
        return favoriteBookListResponse;
    }
}
