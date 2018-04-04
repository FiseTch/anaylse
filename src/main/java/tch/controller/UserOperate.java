package tch.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tch.model.User;
import tch.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserOperate {
	private static Log log = LogFactory.getLog(UserOperate.class);
	@Resource
	IUserService userService;//实现spring的控制反转。对接口的调用
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username,@RequestParam("pwd") String pwd){
		ModelAndView model = new ModelAndView();
		model = checkUser(username,pwd,model);
		return model;		
	}
	public  ModelAndView checkUser(String username,String pwd,ModelAndView model){
		if (null == username || null == pwd) {
			log.error("用户名或密码为空");
			model.setViewName("index");
		}else{
//			ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
//			userService = (IUserService) ac.getBean("userService");
			User u = userService.getUserById(username);
			if (null != u) {				
				model.addObject("user", u);
				model.setViewName("user/downExcel");
			}else{
				model.setViewName("result/uploadFailure");
			}
		}
		return model;
	}
}
