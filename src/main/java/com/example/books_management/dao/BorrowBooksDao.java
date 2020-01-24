package com.example.books_management.dao;

import com.example.books_management.mapper.BorrowBooksMapper;
import com.example.books_management.pojo.BorrowBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class BorrowBooksDao {

    @Autowired
    private BorrowBooksMapper borrowBooksMapper;

    public Integer insertBorrowBooks(BorrowBooks borrowBooks){
        return borrowBooksMapper.insert(borrowBooks);
    }

    public Integer deleteBorrowBooks(Long id){
        return borrowBooksMapper.deleteByPrimaryKey(id);
    }

    public Integer updateBorrowBooks(BorrowBooks borrowBooks){
        return borrowBooksMapper.updateByPrimaryKeySelective(borrowBooks);
    }

    public List<BorrowBooks> selectAllBorrowBooks(){
        return borrowBooksMapper.selectAll();
    }

    public BorrowBooks selectOneBorrowBooksById(Long id){
        return borrowBooksMapper.selectByPrimaryKey(id);
    }

    public List<BorrowBooks> selectBorrowBooksByUserId(Long userId){
        Example example = new Example(BorrowBooks.class);
        example.createCriteria().andEqualTo("userId",userId);
        return borrowBooksMapper.selectByExample(example);
    }

    public List<BorrowBooks> selectBorrowBooksByBookId(Long bookId){
        Example example = new Example(BorrowBooks.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return borrowBooksMapper.selectByExample(example);
    }

    public Integer countAllBorrowBooks(){
        Example example = new Example(BorrowBooks.class);
        return borrowBooksMapper.selectCountByExample(example);
    }

    public Integer countBorrowBooksByUserId(Long userId){
        Example example = new Example(BorrowBooks.class);
        example.createCriteria().andEqualTo("userId",userId);
        return borrowBooksMapper.selectCountByExample(example);
    }

    public Integer countBorrowBooksByBookId(Long bookId){
        Example example = new Example(BorrowBooks.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return borrowBooksMapper.selectCountByExample(example);
    }
}
