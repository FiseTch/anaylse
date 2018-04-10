package tch.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tch.model.User;
import tch.service.IUserService;

@Controller
@Scope("prototype")
@RequestMapping("/userController")
public class UserController {
	
	@Resource
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@RequestMapping("/showUser")
	public String showUser(String username,HttpServletRequest request){
		User u = userService.getUserById(username);
		request.setAttribute("user", u);
		return "showUser";//默认自动添加.jsp
		
	}

}
