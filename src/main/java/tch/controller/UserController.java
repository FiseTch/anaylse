package tch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tch.model.User;
import tch.service.IUserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	
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
