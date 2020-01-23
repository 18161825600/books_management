package com.example.books_management.dao;

import com.example.books_management.mapper.ReturnBooksMapper;
import com.example.books_management.pojo.ReturnBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ReturnBooksDao {

    @Autowired
    private ReturnBooksMapper returnBooksMapper;

    public Integer insertReturnBooks(ReturnBooks returnBooks){
        return returnBooksMapper.insert(returnBooks);
    }

    public Integer deleteReturnBooks(Long id){
        return returnBooksMapper.deleteByPrimaryKey(id);
    }

    public Integer updateReturnBooks(ReturnBooks returnBooks){
        return returnBooksMapper.updateByPrimaryKeySelective(returnBooks);
    }

    public List<ReturnBooks> selectAllReturnBooks(){
        return returnBooksMapper.selectAll();
    }

    public ReturnBooks selectReturnBooksById(Long id){
        return returnBooksMapper.selectByPrimaryKey(id);
    }

    public List<ReturnBooks> selectReturnBooksByUserId(Long userId){
        Example example = new Example(ReturnBooks.class);
        example.createCriteria().andEqualTo("user_id",userId);
        return returnBooksMapper.selectByExample(example);
    }

    public List<ReturnBooks> selectReturnBooksByBookId(Long bookId){
        Example example = new Example(ReturnBooks.class);
        example.createCriteria().andEqualTo("book_id",bookId);
        return returnBooksMapper.selectByExample(example);
    }

    public Integer countAllReturnBook(){
        Example example = new Example(ReturnBooks.class);
        return returnBooksMapper.selectCountByExample(example);
    }

    public Integer countReturnBookByUserId(Long userId){
        Example example = new Example(ReturnBooks.class);
        example.createCriteria().andEqualTo("user_id",userId);
        return returnBooksMapper.selectCountByExample(example);
    }

    public Integer countReturnBookByBookId(Long bookId){
        Example example = new Example(ReturnBooks.class);
        example.createCriteria().andEqualTo("book_id",bookId);
        return returnBooksMapper.selectCountByExample(example);
    }
}
