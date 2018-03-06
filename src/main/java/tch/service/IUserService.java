package tch.service;

import java.util.List;

import tch.model.User;

public interface IUserService {
	
	
	public User getUserById(String username);
	public List<User> getAll();
}
