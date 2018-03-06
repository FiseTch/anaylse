package tch.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tch.model.User;
import tch.service.IUserService;

@Controller
@RequestMapping(value = "/excelTest")
public class ExcelTest {
	
	private static Log  log = LogFactory.getLog(ExcelTest.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/test")
	public String Test(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request){
		User user = userService.getUserById(username);
		if (password != null && password.equals(user.getPassword())) {
			request.setAttribute("user", user);
			return "showUser";
		}else{
			log.error("密码错误:"+ user.getPassword());
			return "error";
		}
	}
}