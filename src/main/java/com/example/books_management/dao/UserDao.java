package com.example.books_management.dao;

import com.example.books_management.mapper.UserMapper;
import com.example.books_management.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public Integer insertUser(User user){
        return userMapper.insert(user);
    }

    public Integer updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectOneUserById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> selectAllUser(){
        return userMapper.selectAll();
    }

    public User selectOneUserByOpenId(Long openId){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("open_id",openId);
        return userMapper.selectOneByExample(example);
    }

    public Integer countAllUser(){
        Example example = new Example(User.class);
        return userMapper.selectCountByExample(example);
    }
}
