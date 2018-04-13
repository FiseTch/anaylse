package tch.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tch.model.User;
import tch.service.IUserService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/user")
public class UserAction {
	private static Log log = LogFactory.getLog(UserAction.class);
	
	@Resource
	private IUserService userService;//实现spring的控制反转。对接口的调用
	
	

	/**
	 * 
	 * @user: tongchaohua
	 * @Title: login
	 * @Description: 用户登录
	 * @param username
	 * @param pwd
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username,@RequestParam("pwd") String pwd){		
		ModelAndView model = new ModelAndView(); 
		User user = new User();
		try {
			user.setUsername(URLDecoder.decode(username,"UTF-8"));
			user.setPassword(URLDecoder.decode(pwd,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName()+"出错，页面编码错误");
			log.error(e);
			e.printStackTrace();
		}
		
//			ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
//			userService = (IUserService) ac.getBean("userService");
		
		User u = userService.getUserByAttr(user);
		if (u != null) {						
			model.addObject("user",u);
			model.setViewName("login");
		}else{
			model.setViewName("view/result/uploadFailure");			
		}
		return model;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(){
		ModelAndView model = new ModelAndView();		
		return model; 
	}
		

}
