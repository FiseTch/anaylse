package tch.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tch.impl.UserServiceImpl;
import tch.model.User;
import tch.util.ExcelRead;
import tch.util.ExcelUtil;

public class Excel {

	@RequestMapping(value="/read")  
	public String addExcel(){  
	    return "baseInfo/testExcel";  
	} 
	/** 
	 * 
	 * @user: tongchaohua
	 * @Title: readExcel
	 * @Description: TODO
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/readExcel")   
	public ModelAndView readExcel(@RequestParam(value="excelFile") MultipartFile file,HttpServletRequest request,HttpSession session) throws IOException{  
	    ModelAndView mv = new ModelAndView();  
	    //判断文件是否为空  
	    if(file == null){  
	        mv.addObject("msg", "failed");  
	        mv.setViewName("excel_result");  
	        return mv;  
	    }  
	    String name = file.getOriginalFilename();  
	    long size = file.getSize();  
	    if(name == null || ExcelUtil.EMPTY.equals(name) && size==0){  
	        mv.addObject("msg", "failed");  
	        mv.setViewName("excel_result");  
	        return mv;  
	    }  
	    //读取Excel数据到List中  
	    List<ArrayList<String>> list = new ExcelRead().readExcel(file);  
	               //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：  
	    User user = null;  
	    List<User> listUser = new ArrayList<User>();  
	    for(ArrayList<String> arr:list){                        
	        user= new User();                         
	        user.setAuthor(list.get(0));//每一行的第一个单元格  
	        listUser.add(user);  
	    }  
	    if(UserServiceImpl.saveBatchInsert(listUser)){  
	        mv.addObject("msg", "success");  
	    }else{  
	        mv.addObject("msg", "failed");  
	    }                     
	    mv.setViewName("excel_result");  
	    return mv;  
	}
}
