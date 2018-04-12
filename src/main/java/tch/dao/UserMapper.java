package tch.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tch.model.User;


public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User user);

    int insertSelective(User user);

    User getUserByPrimaryKey(String username);
    
    User getUserByAttr(User user);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
    List<User> getAll();
}