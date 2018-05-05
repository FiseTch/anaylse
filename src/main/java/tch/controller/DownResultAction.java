package tch.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tch.model.ReviewResult;
import tch.service.IReviewResultService;
import tch.util.ExcelDownUtil;
import tch.util.MyCommonUtil;



@Controller
@RequestMapping(value = "/downResult")
public class DownResultAction {
	private static Log log = LogFactory.getLog(DownResultAction.class);
	
	@Resource
	private IReviewResultService ReviewResultService;

	/**
	 * 
	 * @user: tongchaohua
	 * @Title: downResult
	 * @Description:	将解析结果导出到excel
	 * @param request
	 * @param response
	 * @return
	 * @return: ModelAndView
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/downResult")
	public ModelAndView downResult(@RequestParam("id") String id) throws UnsupportedEncodingException, IOException{
		ModelAndView model = null;
		Map<String,ReviewResult> resultMap = new HashMap<String, ReviewResult>();		
		id = MyCommonUtil.changeEncode(id);
		if(id != null){
			ReviewResult reviewResult = ReviewResultService.getRevById(id);
			resultMap.put("reviewResult", reviewResult);
			ExcelDownUtil ve = new ExcelDownUtil();  	    
			log.info(new Date().toLocaleString());
			model = new ModelAndView(ve,resultMap);
		}else{
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
			model.addObject("errorMsg","当前传入试卷id为空");
			model.setViewName("view/result/uploadFailure");
		}
		return model;  		
	}
}
