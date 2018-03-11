package tch.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tch.util.ExcelDownUtil;



@Controller
@RequestMapping(value = "/downResult")
public class DownResult {
	private static Log log = LogFactory.getLog(DownResult.class);

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
	@RequestMapping(value = "/downResult")
	public ModelAndView downResult(HttpSession session) throws UnsupportedEncodingException, IOException{
		Map<String,Double> resultMap = new HashMap<String, Double>();		
		resultMap  = (Map<String, Double>) session.getAttribute("resultMap");		
		ExcelDownUtil ve = new ExcelDownUtil();  	    
	    log.info(new Date().toLocaleString());
	    return new ModelAndView(ve,resultMap);  		
	}
}
