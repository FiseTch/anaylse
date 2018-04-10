package tch.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tch.model.User;
import tch.util.ExcelExportUtil;

@Controller
@Scope("prototype")
@RequestMapping("/excelTempleDown")

public class ExcelTempleDown {
	
	private Log log = LogFactory.getLog(ExcelTempleDown.class); 
	@RequestMapping(value = "/downloadExcel")
	public ModelAndView downloanExcel(@RequestParam("num") String num){ 
		Map<String,Integer> map = new HashMap<String, Integer>();
		int totalNum = 0;
		if(null != num.trim()){
			totalNum = Integer.parseInt(num);
		}
		map.put("totalNum",(totalNum == 0) ? 50 : totalNum);
	    /* List<AuContract> list = new ArrayList<AuContract>();	   
	     list= service.findAuContractList();//获得数据库所有的合同集合
	     Map<String,List<AuContract>> map = new HashMap<String, List<AuContract>>();  
	     map.put("infoList", list); */ 
	    ExcelExportUtil ve = new ExcelExportUtil();  	    
	    log.info(new Date().toLocaleString());
	    return new ModelAndView(ve,map);  
	 } 

}
