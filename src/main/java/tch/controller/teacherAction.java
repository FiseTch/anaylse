package tch.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tch.dao.TeacherMapper;
import tch.model.Teacher;
import tch.service.ITeacherService;
import tch.util.MyCommonUtil;

@Controller
@Scope("prototype")
@RequestMapping(value = "/teacher")
public class TeacherAction {	
	private static final Log log = LogFactory.getLog(TeacherAction.class);
	
	@Resource
	private ITeacherService teacherService; 
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: login
	 * @Description: 用户登录
	 * @param username
	 * @param pwd
	 * @param session
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username,@RequestParam("pwd") String pwd,HttpSession session){
		ModelAndView model = new ModelAndView();
		Teacher teacher = new Teacher();
		try {
			teacher.setId(MyCommonUtil.changeEncode(username));
			Teacher t = teacherService.getTeacById(username);
			if (null != t) {
				if(MyCommonUtil.changeEncode(pwd).equals(t.getPassword())){
					session.setAttribute("userId", t.getId());
					session.setAttribute("teacher", t);
					model.addObject("teacher",t);
					model.setViewName("login");
				}else{
					model.addObject("errorMsg", "用户密码错误！！！");
					model.addObject("logMsg","查询数据成功："+Thread.currentThread().getStackTrace()[1].getMethodName());
					model.setViewName("view/result/uploadFailure");	
				}
			}else{
				model.addObject("errorMsg", "用户未注册！！！");
				model.addObject("logMsg","查询数据失败："+Thread.currentThread().getStackTrace()[1].getMethodName());
				model.setViewName("view/result/uploadFailure");	
			}
		} catch (UnsupportedEncodingException e) {
			log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName()+"出错，页面编码错误");
			e.printStackTrace();
		}		
		return model;		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: update
	 * @Description: 对用户信息进行更新
	 * @param name
	 * @param sex
	 * @param birthday
	 * @param tel
	 * @param prof
	 * @param hiredate
	 * @param depart
	 * @param subject
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/update")
	public ModelAndView update(@RequestParam("name")String name,@RequestParam("sex")String sex,
			@RequestParam("birthday")String birthday,@RequestParam("tel")String tel,@RequestParam("prof")String prof,
			@RequestParam("hiredate")String hiredate,@RequestParam("depart")String depart,
			@RequestParam("subject")String subject,HttpSession session) throws UnsupportedEncodingException{
				ModelAndView model = new ModelAndView();
				Teacher teacher = new Teacher();
				teacher.setId((String)session.getAttribute("userId"));
				teacher.setName(MyCommonUtil.changeEncode(name));
				teacher.setSex(MyCommonUtil.changeEncode(sex));
				teacher.setTel(MyCommonUtil.changeEncode(tel));
				teacher.setBirthday(MyCommonUtil.getDateFormat(birthday));
				teacher.setProf(MyCommonUtil.changeEncode(prof));
				teacher.setDepart(MyCommonUtil.changeEncode(depart));
				teacher.setHiredate(MyCommonUtil.getDateFormat(hiredate));
				teacher.setSubject(MyCommonUtil.changeEncode(subject));
				int i = teacherService.updateByIdSelective(teacher);//进行更新操作
				if (i == 1) {
					teacher = teacherService.getTeacById((String)session.getAttribute("userId"));
					if (teacher != null) {
						session.removeAttribute("teacher");
						session.setAttribute("teacher", teacher);
						model.addObject("teacher",teacher);
						model.setViewName("myInformation");				
					}else{
						model.addObject("errorMsg", "登录成功，查询用户个人数据失败！！！");
						model.addObject("logMsg", "数据库插入数据失败"+Thread.currentThread().getStackTrace()[1].getMethodName());
						model.setViewName("view/result/uploadFailure");
					}
				}else{
					model.addObject("errorMsg", "用户修改个人信息失败！！！");
					model.addObject("logMsg","数据库更新失败："+Thread.currentThread().getStackTrace()[1].getMethodName());
					model.setViewName("view/result/uploadFailure");
				}
				return model;
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: register
	 * @Description: 用户注册
	 * @param id
	 * @param username
	 * @param password
	 * @param confirmPassword
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/register")
	public ModelAndView register(@RequestParam("id")String id,@RequestParam("username")String username,
			@RequestParam("password")String password,@RequestParam("confirmPassword")String confirmPassword,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView model = new ModelAndView();
		Teacher teacher = new Teacher();
		if (!isRegisterUser(id)) {			
			teacher.setId(MyCommonUtil.changeEncode(id));
			teacher.setName(MyCommonUtil.changeEncode(username));
			teacher.setPassword(MyCommonUtil.changeEncode(confirmPassword));
			int i = teacherService.insertTeacherSelective(teacher);
			if (i == 1) {
				Teacher t = teacherService.getTeacById(id);
				if (t != null) {
					session.removeAttribute("teacher");
					session.setAttribute("teacher", t);
					model.setViewName("login");
				}else{
					model.addObject("errorMsg", "注册成功，查询用户个人数据失败！！！");
					model.addObject("logMsg", "数据库插入数据失败"+Thread.currentThread().getStackTrace()[1].getMethodName());
					model.setViewName("view/result/uploadFailure");
				}
			}else{
				model.addObject("errorMsg", "注册失败！！！");
				model.addObject("logMsg", "数据库插入数据失败"+Thread.currentThread().getStackTrace()[1].getMethodName());
				model.setViewName("view/result/uploadFailure");
			}
		}else{
			model.addObject("errorMsg", "该用户已被注册");
			model.addObject("logMsg", "数据库插入数据失败"+Thread.currentThread().getStackTrace()[1].getMethodName());
			model.setViewName("view/result/uploadFailure");
		}
		return model;
		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: changePwd
	 * @Description: 用户修改密码操作
	 * @param oldPassword
	 * @param newPassword
	 * @param newPasswordRepeat
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/changePassword")
	public ModelAndView changePwd(@RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword,
			@RequestParam("newPasswordRepeat")String newPasswordRepeat,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView model = new ModelAndView();
		Teacher teacher = new Teacher();
		if (newPassword.equals(newPasswordRepeat)) {
			teacher.setId((String)session.getAttribute("userId"));
			teacher.setPassword(MyCommonUtil.changeEncode(newPasswordRepeat));
			int i = teacherService.updateByIdSelective(teacher);//进行更新操作
			if (i == 1) {
				teacher = teacherService.getTeacById((String)session.getAttribute("userId"));
				if (teacher != null) {
					session.removeAttribute("teacher");
					session.setAttribute("teacher", teacher);
					model.setViewName("changePassword");
				}else{
					model.addObject("errorMsg", "修改密码成功，查询用户个人数据失败！！！");
					model.addObject("logMsg", "数据库插入数据失败"+Thread.currentThread().getStackTrace()[1].getMethodName());
					model.setViewName("view/result/uploadFailure");
				}
			}else{
				model.addObject("errorMsg", "用户修改密码失败！！！");
				model.addObject("logMsg","数据库更新失败："+Thread.currentThread().getStackTrace()[1].getMethodName());
				model.setViewName("view/result/uploadFailure");
			}
		}else{
			model.addObject("errorMsg", "两次密码不一致"+Thread.currentThread().getStackTrace()[1].getMethodName());
			model.setViewName("view/result/uploadFailure");
		}
		return model;
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: checkUser
	 * @Description: 校验用户是否已经被注册
	 * @param userId
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/checkUser",method = RequestMethod.GET)
	public ModelAndView checkUser(@RequestParam("userId")String userId){
		ModelAndView model = new ModelAndView();
		if (!isRegisterUser(userId)) {//如没被注册，返回真
			model.addObject("isRegister", true);
			model.setViewName("register");
		}else{
			model.addObject("isRegister", false);
			model.setViewName("register");
		}
		return model;
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: logout
	 * @Description: 用户退出登录
	 * @param httpSession
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession httpSession){
		httpSession.invalidate();
		return "logout";
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: isRegisterUser
	 * @Description: 判断用户是否已经注册
	 * 若已注册返回true，未注册返回false
	 * @param userId
	 * @return
	 * @return: boolean
	 */
	public  boolean isRegisterUser(String userId){
		if (userId != null) {
			List<String> s = teacherService.getAllTeacId();
			if (null != s) {
				if (s.contains(userId)) {
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
