package tch.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import tch.dao.UserMapper;
import tch.model.User;
import tch.service.IUserService;
import tch.util.ConstantTch;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource//放在set方法也可以，不过需要考虑名称与属性类型的问题
	private UserMapper userMapper;
	
/*	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired与resource的区别在于一个根据名称，一个根据属性类型
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}*/
	
	/**
	 * 通过id查询用户
	 */
	public User getUserById(String username) {	
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		User user = new User();
		user = userMapper.selectByPrimaryKey(username);
		if (user != null) {
			log.info("user:" + user.toString());
			return user;
		}else{
			log.error("当前查询用户为空");
			return ConstantTch.DEFAULT_USER;
		}
	}
	
	/**
	 * 查询所有用户
	 */	
	public List<User> getAllUser() {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return userMapper.getAll() ;
	}
	 
/*	public static boolean saveBatchInsert(List<User> listUser) {		
		return false;
	}*/

	/**
	 * 插入记录，不允许为空
	 */
	public int insertUser(User user) {	
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return userMapper.insert(user);
	}

	/**
	 * 插入记录，允许为空（包括主键）
	 */
	public int insertUserSelective(User user) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return userMapper.insertSelective(user);
	}

	/**
	 * 根据主键id 删除记录
	 */
	public void deleteUserById(String username) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		userMapper.deleteByPrimaryKey(username);
	}

	/**
	 * 根据主键id更新记录（其余属性不允许为空）
	 */
	public int updateUserById(User user) {	
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return userMapper.updateByPrimaryKey(user);
	}

	/**
	 * 根据主键id更新记录（其余属性允许为空）
	 */
	public int updateUserByIdSelective(User user) {	
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return userMapper.updateByPrimaryKeySelective(user);
	}
}
