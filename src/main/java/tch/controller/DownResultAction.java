package tch.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tch.util.ExcelDownUtil;



@Controller
@RequestMapping(value = "/downResult")
public class DownResultAction {
	private static Log log = LogFactory.getLog(DownResultAction.class);

	/**
	 * 
	 * @user: tongchaohua
	 * @Title: downResult
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @return: ModelAndView
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/downResult")
	public ModelAndView downResult(HttpSession session) throws UnsupportedEncodingException, IOException{
		Map<String,Double> resultMap = new HashMap<String, Double>();		
		resultMap  = (Map<String, Double>) session.getAttribute("resultMap");		
		ExcelDownUtil ve = new ExcelDownUtil();  	    
	    log.info(new Date().toLocaleString());
	    return new ModelAndView(ve,resultMap);  		
	}
}
