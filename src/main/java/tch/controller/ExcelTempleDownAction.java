package tch.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import tch.util.ExcelExportUtil;

@Controller
@RequestMapping("/excelTempleDown")

public class ExcelTempleDownAction {
	
	private Log log = LogFactory.getLog(ExcelTempleDownAction.class); 
	@SuppressWarnings("deprecation")
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
