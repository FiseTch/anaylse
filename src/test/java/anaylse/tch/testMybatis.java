package anaylse.tch;



import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.util.ClassPath;

import tch.model.User;
import tch.service.IUserService;

public class testMybatis {
	private static final Logger log = Logger.getLogger(testMybatis.class);
	private ApplicationContext ac;
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Before
	public void before(){
		 ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
		 userService = (IUserService) ac.getBean("userService");
		
	}
	@Test
	public void test1() throws Exception{
		List<User> u = userService.getAll();				
		log.info(JSON.toJSONString(u));
		
	}

}
