package com.example.books_management.service.impl;

import com.example.books_management.dao.BookDao;
import com.example.books_management.dao.BorrowBooksDao;
import com.example.books_management.dao.UserDao;
import com.example.books_management.pojo.Book;
import com.example.books_management.pojo.BorrowBooks;
import com.example.books_management.pojo.User;
import com.example.books_management.request.AddBorrowBookRequest;
import com.example.books_management.request.updateBorrowBookTermRequest;
import com.example.books_management.response.BorrowBooksResponse;
import com.example.books_management.service.BorrowBooksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowBooksServiceImpl implements BorrowBooksService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BorrowBooksDao borrowBooksDao;

    @Override
    public Integer insertBorrowBooks(Long userId,AddBorrowBookRequest addBorrowBookRequest) {
        BorrowBooks borrowBooks = new BorrowBooks();
        if(addBorrowBookRequest.getTerm()<=0){
            return -1;//借书期限不能小于0
        }else if(addBorrowBookRequest.getTerm()>30){
            return -2;//借书期限不能大于30
        }else {
            User user = userDao.selectOneUserById(userId);
            if(user.getDeposit()==50) {
                borrowBooks.setTerm(addBorrowBookRequest.getTerm());

                Book book = bookDao.selectOneBookByBookName(addBorrowBookRequest.getBookName());
                book.setSurplusNumber(book.getSurplusNumber()-1);
                book.setUpdateTime(new Date());
                bookDao.updateBook(book);

                borrowBooks.setBookId(book.getId());

                borrowBooks.setUserId(userId);
                borrowBooks.setState((short) 1);
                borrowBooks.setCreateTime(new Date());

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.roll(calendar.DAY_OF_YEAR,addBorrowBookRequest.getTerm());
                borrowBooks.setDueTime(calendar.getTime());

                return borrowBooksDao.insertBorrowBooks(borrowBooks);
            }else return -3;//押金不足
        }
    }

    @Override
    public Integer deleteBorrowBooks(Long id) {
        return borrowBooksDao.deleteBorrowBooks(id);
    }

    @Override
    public Integer updateBorrowBooks(updateBorrowBookTermRequest updateBorrowBookTermRequest) {
        BorrowBooks borrowBooks = borrowBooksDao.selectOneBorrowBooksById(updateBorrowBookTermRequest.getBorrowId());
        if(updateBorrowBookTermRequest.getTerm()<=0){
            return -1;//续借时间不能小于0;
        }else if(updateBorrowBookTermRequest.getTerm()>30){
            return -2;//续借时间不能大于30天
        }else {
            borrowBooks.setTerm(borrowBooks.getTerm()+updateBorrowBookTermRequest.getTerm());
            borrowBooks.setUpdateTime(new Date());
            borrowBooks.setState((short)0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(borrowBooks.getDueTime());
            calendar.roll(Calendar.DAY_OF_YEAR,updateBorrowBookTermRequest.getTerm());
            borrowBooks.setDueTime(calendar.getTime());

            return borrowBooksDao.updateBorrowBooks(borrowBooks);
        }
    }

    @Override
    public List<BorrowBooksResponse> selectAllBorrowBooks(Integer pageNum) {
        List<BorrowBooks> borrowBooks = borrowBooksDao.selectAllBorrowBooks();
        PageHelper.startPage(pageNum,10);
        List<BorrowBooksResponse> list = new ArrayList<>();
        for (BorrowBooks borrowBook : borrowBooks){
            BorrowBooksResponse borrowBooksResponse = changeBorrowBookResponse(borrowBook);
            borrowBooksResponse.setTotal(borrowBooksDao.countAllBorrowBooks());

            list.add(borrowBooksResponse);
        }
        PageInfo<BorrowBooksResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public BorrowBooksResponse selectOneBorrowBooksById(Long id) {
        BorrowBooks borrowBook = borrowBooksDao.selectOneBorrowBooksById(id);
        return changeBorrowBookResponse(borrowBook);
    }

    @Override
    public List<BorrowBooksResponse> selectBorrowBooksByUserId(Long userId, Integer pageNum) {
        List<BorrowBooks> borrowBooks = borrowBooksDao.selectBorrowBooksByUserId(userId);
        PageHelper.startPage(pageNum,10);
        List<BorrowBooksResponse> list = new ArrayList<>();
        for(BorrowBooks borrowBook : borrowBooks){
            BorrowBooksResponse borrowBooksResponse = changeBorrowBookResponse(borrowBook);
            borrowBooksResponse.setTotal(borrowBooksDao.countBorrowBooksByUserId(userId));

            list.add(borrowBooksResponse);
        }
        PageInfo<BorrowBooksResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<BorrowBooksResponse> selectBorrowBooksByBookId(Long bookId, Integer pageNum) {
        List<BorrowBooks> borrowBooks = borrowBooksDao.selectBorrowBooksByBookId(bookId);
        PageHelper.startPage(pageNum,10);
        List<BorrowBooksResponse> list = new ArrayList<>();
        for (BorrowBooks borrowBook : borrowBooks){
            BorrowBooksResponse borrowBooksResponse = changeBorrowBookResponse(borrowBook);
            borrowBooksResponse.setTotal(borrowBooksDao.countBorrowBooksByBookId(bookId));

            list.add(borrowBooksResponse);
        }
        PageInfo<BorrowBooksResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    public BorrowBooksResponse changeBorrowBookResponse(BorrowBooks borrowBook){
        BorrowBooksResponse borrowBooksResponse = new BorrowBooksResponse();
        borrowBooksResponse.setState(borrowBook.getState());
        borrowBooksResponse.setTerm(borrowBook.getTerm());
        borrowBooksResponse.setCreateTime(borrowBook.getCreateTime());

        User user = userDao.selectOneUserById(borrowBook.getUserId());
        borrowBooksResponse.setNickName(user.getNickName());

        Book book = bookDao.selectOneById(borrowBook.getBookId());
        borrowBooksResponse.setBookName(book.getBookName());
        borrowBooksResponse.setAuthorName(book.getAuthorName());
        borrowBooksResponse.setBookPrice(book.getBookPrice());
        borrowBooksResponse.setCoverUrl(book.getCoverUrl());
        return borrowBooksResponse;
    }
}
