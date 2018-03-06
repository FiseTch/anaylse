package tch.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import tch.dao.UserMapper;
import tch.model.User;
import tch.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User getUserById(String username) {		
		return userMapper.selectByPrimaryKey(username);
	}

	public List<User> getAll() {
		
		return userMapper.getAll() ;
	}
	 
	public static boolean saveBatchInsert(List<User> listUser) {
		
		return false;
	}
}
