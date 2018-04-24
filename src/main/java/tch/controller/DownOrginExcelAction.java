package tch.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tch.util.ExcelUpDownUtil;


@Controller
@RequestMapping("downOrginExcel")
public class DownOrginExcelAction {
	
	private static Log log = LogFactory.getLog(DownOrginExcelAction.class);
	
	@RequestMapping("/downOrginExcel")
	public ModelAndView downResult(HttpSession session) throws UnsupportedEncodingException, IOException{
		Map<String,Map> resultMap = new HashMap<String, Map>();
		Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();		
		data  = (Map<Integer, List<String>>) session.getAttribute("data");	
		resultMap.put("data", data);
		ExcelUpDownUtil ve = new ExcelUpDownUtil();  	    
	    log.info(new Date().toLocaleString());
	    return new ModelAndView(ve,resultMap);  		
	}

}
