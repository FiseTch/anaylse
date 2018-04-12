package tch.service;

import java.util.List;

import tch.model.User;

public interface IUserService {
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getUserById
	 * @Description: 通过主键username查询用户
	 * @param username
	 * @return
	 * @return: User
	 */
	public User getUserById(String username);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getUserByAttr
	 * @Description: 根据属性值查记录
	 * @param user
	 * @return
	 * @return: User
	 */
	public User getUserByAttr(User user);	
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getAll
	 * @Description: 查询所有用户
	 * @return
	 * @return: List<User>
	 */
	public List<User> getAllUser();
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: insert
	 * @Description: 新增记录不允许为空
	 * @param user
	 * @return
	 * @return: int
	 */
	public int insertUser(User user);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: insertSelective
	 * @Description: 新增记录，允许为空
	 * @param user
	 * @return
	 * @return: int
	 */
	public int insertUserSelective(User user);
	
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: updateUserById
	 * @Description: 通过id来更新记录
	 * @param user
	 * @return
	 * @return: int
	 */
	public int updateUserById(User user);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: updateUserByIdSelective
	 * @Description: 通过id更新记录，允许为空，若全部为空时，则意味着记录没有更新
	 * @param user
	 * @return
	 * @return: int
	 */
	public int updateUserByIdSelective(User user);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: deleteUserById
	 * @Description: 通过username删除用户
	 * @param username
	 * @return: void
	 */
	public void deleteUserById(String username);
}
