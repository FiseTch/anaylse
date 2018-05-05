package tch.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tch.model.Paper;
import tch.service.IPaperService;
import tch.util.ExcelUpDownUtil;
import tch.util.MyCommonUtil;


@Controller
@RequestMapping("downOrginExcel")
public class DownOrginExcelAction {
	
	private static Log log = LogFactory.getLog(DownOrginExcelAction.class);
	
	@Resource
	private IPaperService paperService;
	
	@RequestMapping("/downOrginExcel")
	public ModelAndView downResult(@RequestParam("paperId")String paperId,HttpSession session) throws UnsupportedEncodingException, IOException{
		paperId = MyCommonUtil.changeEncode(paperId);	
		if(paperId != null){
			Paper paper = new Paper();
			paper.setPaperid(paperId);
			List<Paper> paperList = paperService.getPaperByAttr(paper);
			Map<String,List<Paper>> paperMap = new HashMap<String,List<Paper>>();
			paperMap.put("paperList", paperList);
			ExcelUpDownUtil ve = new ExcelUpDownUtil();  	    
			log.info(new Date().toLocaleString());
			return new ModelAndView(ve,paperMap);  		
		}else{
			ModelAndView model = new ModelAndView();
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
			model.addObject("logMsg", "上传文件为空："+Thread.currentThread().getStackTrace()[1].getMethodName());
			model.setViewName("view/result/uploadFailure");
			return model;
		}
	}

}
