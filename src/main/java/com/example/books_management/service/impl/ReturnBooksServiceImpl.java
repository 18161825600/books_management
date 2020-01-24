package com.example.books_management.service.impl;

import com.example.books_management.dao.BookDao;
import com.example.books_management.dao.BorrowBooksDao;
import com.example.books_management.dao.ReturnBooksDao;
import com.example.books_management.dao.UserDao;
import com.example.books_management.pojo.Book;
import com.example.books_management.pojo.BorrowBooks;
import com.example.books_management.pojo.ReturnBooks;
import com.example.books_management.pojo.User;
import com.example.books_management.response.ReturnBooksResponse;
import com.example.books_management.service.ReturnBooksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReturnBooksServiceImpl implements ReturnBooksService {

    @Autowired
    private ReturnBooksDao returnBooksDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BorrowBooksDao borrowBooksDao;

    @Override
    public Integer insertReturnBooks(Long borrowBooksId) {
        BorrowBooks borrowBooks = borrowBooksDao.selectOneBorrowBooksById(borrowBooksId);
        ReturnBooks returnBooks = new ReturnBooks();
        returnBooks.setBookId(borrowBooks.getBookId());
        returnBooks.setBorrowId(borrowBooksId);
        returnBooks.setUserId(borrowBooks.getUserId());
        returnBooks.setCreateTime(new Date());

        //拿到借书的日期和当前日期
        Date oldTime = borrowBooks.getCreateTime();
        Date nowTime = new Date();

        Long day =(nowTime.getTime()-oldTime.getTime())/(24*60*60*1000);
        if(day <= borrowBooks.getTerm()){
            borrowBooks.setState((short)2);
            borrowBooksDao.updateBorrowBooks(borrowBooks);

            Book book = bookDao.selectOneById(borrowBooks.getBookId());
            book.setSurplusNumber(book.getSurplusNumber()+1);
            book.setUpdateTime(new Date());
            bookDao.updateBook(book);

            returnBooks.setIndemnity(0.0);
            return returnBooksDao.insertReturnBooks(returnBooks);
        }else{
            borrowBooks.setState((short)3);
            borrowBooks.setUpdateTime(new Date());
            borrowBooksDao.updateBorrowBooks(borrowBooks);

            Double fine =(day-borrowBooks.getTerm())*0.5;//逾期罚款金额

            User user = userDao.selectOneUserById(borrowBooks.getUserId());
            user.setDeposit(user.getDeposit()-fine);
            user.setUpdateTime(new Date());
            userDao.updateUser(user);

            Book book = bookDao.selectOneById(borrowBooks.getBookId());
            book.setSurplusNumber(book.getSurplusNumber()+1);
            book.setUpdateTime(new Date());
            bookDao.updateBook(book);

            returnBooks.setIndemnity(fine);
            return returnBooksDao.insertReturnBooks(returnBooks);
        }
    }

    @Override
    public Integer deleteReturnBooks(Long id) {
        return returnBooksDao.deleteReturnBooks(id);
    }

    @Override
    public List<ReturnBooksResponse> selectAllReturnBooks(Integer pageNum) {
        List<ReturnBooks> returnBooks = returnBooksDao.selectAllReturnBooks();
        PageHelper.startPage(pageNum,10);
        List<ReturnBooksResponse> list = new ArrayList<>();
        for(ReturnBooks returnBook : returnBooks){
            ReturnBooksResponse returnBooksResponse = changeReturnBookResponse(returnBook);
            returnBooksResponse.setTotal(returnBooksDao.countAllReturnBook());
            list.add(returnBooksResponse);
        }
        PageInfo<ReturnBooksResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public ReturnBooksResponse selectReturnBooksById(Long id) {
        ReturnBooks returnBooks = returnBooksDao.selectReturnBooksById(id);
        return changeReturnBookResponse(returnBooks);
    }

    @Override
    public List<ReturnBooksResponse> selectReturnBooksByUserId(Long userId, Integer pageNum) {
        List<ReturnBooks> returnBooks = returnBooksDao.selectReturnBooksByUserId(userId);
        PageHelper.startPage(pageNum,10);
        List<ReturnBooksResponse> list = new ArrayList<>();
        for(ReturnBooks returnBook : returnBooks){
            ReturnBooksResponse returnBooksResponse = changeReturnBookResponse(returnBook);
            returnBooksResponse.setTotal(returnBooksDao.countReturnBookByUserId(userId));
            list.add(returnBooksResponse);
        }
        PageInfo<ReturnBooksResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<ReturnBooksResponse> selectReturnBooksByBookId(Long bookId, Integer pageNum) {
        List<ReturnBooks> returnBooks = returnBooksDao.selectReturnBooksByBookId(bookId);
        PageHelper.startPage(pageNum,10);
        List<ReturnBooksResponse> list = new ArrayList<>();
        for(ReturnBooks returnBook : returnBooks){
            ReturnBooksResponse returnBooksResponse = changeReturnBookResponse(returnBook);
            returnBooksResponse.setTotal(returnBooksDao.countReturnBookByBookId(bookId));
            list.add(returnBooksResponse);
        }
        PageInfo<ReturnBooksResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    public ReturnBooksResponse changeReturnBookResponse(ReturnBooks returnBook){
        ReturnBooksResponse returnBooksResponse = new ReturnBooksResponse();
        Book book = bookDao.selectOneById(returnBook.getBookId());
        BeanUtils.copyProperties(book,returnBooksResponse);

        User user = userDao.selectOneUserById(returnBook.getUserId());
        returnBooksResponse.setNickName(user.getNickName());

        BorrowBooks borrowBooks = borrowBooksDao.selectOneBorrowBooksById(returnBook.getBorrowId());
        returnBooksResponse.setState(borrowBooks.getState());

        returnBooksResponse.setCreateTime(returnBook.getCreateTime());
        returnBooksResponse.setIndemnity(returnBook.getIndemnity());

        return returnBooksResponse;
    }
}
