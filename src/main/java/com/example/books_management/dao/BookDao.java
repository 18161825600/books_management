package com.example.books_management.dao;

import com.example.books_management.BooksManagementApplication;
import com.example.books_management.mapper.BookMapper;
import com.example.books_management.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class BookDao {

    @Autowired
    private BookMapper bookMapper;

    public Integer insertBook(Book book){
        return bookMapper.insert(book);
    }

    public Integer insertBooks(List<Book> books){
        return bookMapper.insertList(books);
    }

    public Integer deleteBook(Long id){
        return bookMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBooks(List<Long> ids){
        Example example = new Example(Book.class);
        example.createCriteria().andIn("id",ids);
        return bookMapper.deleteByExample(example);
    }

    public Integer updateBook(Book book){
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    public List<Book> selectAllBooks(){
        return bookMapper.selectAll();
    }

    public Book selectOneById(Long id){
        return bookMapper.selectByPrimaryKey(id);
    }

    public List<Book> selectBookByBookName(String bookName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("book_name","%"+bookName+"%");
        return bookMapper.selectByExample(example);
    }

    public Book selectOneBookByBookName(String bookName){
        Example example = new Example(Book.class);
        example.createCriteria().andEqualTo("book_name",bookName);
        return bookMapper.selectOneByExample(example);
    }

    public List<Book> selectBookByAuthorName(String authorName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("author_name","%"+authorName+"%");
        return bookMapper.selectByExample(example);
    }

    public Integer countAllBooks(){
        Example example = new Example(Book.class);
        return bookMapper.selectCountByExample(example);
    }

    public Integer countByBookName(String bookName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("book_name","%"+bookName+"%");
        return bookMapper.selectCountByExample(example);
    }

    public Integer countByAuthorName(String authorName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("author_name","%"+authorName+"%");
        return bookMapper.selectCountByExample(example);
    }

}
