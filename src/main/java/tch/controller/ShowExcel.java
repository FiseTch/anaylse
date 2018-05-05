package tch.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tch.model.Paper;
import tch.service.IPaperService;


@Controller
@RequestMapping("/showExcel")
public class ShowExcel {
	
	
	@Resource
	private IPaperService paperService;
	
	
	@RequestMapping(value = "/showExcel")
	public ModelAndView showExcel(@RequestParam("paperId")String paperId,HttpSession session){
		ModelAndView model = new ModelAndView();
		Paper paper = new Paper();
		List<Paper> paperList = paperService.getPaperByAttr(paper);
		List<Paper> newPaperList = sortPaperList(paperList);
		Map<Integer,List<String>> data = new HashMap<Integer,List<String>>();		
		if(newPaperList != null && newPaperList.size() > 0){
			for (int i = 0; i < newPaperList.size(); i++) {
				List<String> paramList = new ArrayList<String>();
				String[] params = convertToString(newPaperList.get(i));
				for(int j = 0; j<newPaperList.get(0).getNum();j++){//总共有多少列
					paramList.add(params[j]);
				}
				data.put(i+1, paramList);//从1开始保存
			}
			model.addObject("paperId", paperId);
			model.addObject("data", data);
			model.setViewName("showExcel");
		}else{
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
			model.setViewName("/view/result/uploadFailure");
		}
		
		return model;
		
	}
	private String[] convertToString(Paper paper){
		String[] paramList = new String[]{null,null,null,null,null,    null,null,null,null,null,
        		null,null,null,null,null,    null,null,null,null,null,       null,null,null,null,null};//长度为25					
		paramList[0] = paper.getParam1();
		paramList[1] = paper.getParam2();
		paramList[2] = paper.getParam3();
		paramList[3] = paper.getParam4();
		paramList[4] = paper.getParam5();
		paramList[5] = paper.getParam6();
		paramList[6] = paper.getParam7();
		paramList[7] = paper.getParam8();
		paramList[8] = paper.getParam9();
		paramList[9] = paper.getParam10();
		paramList[10] = paper.getParam11();
		paramList[11] = paper.getParam12();
		paramList[12] = paper.getParam13();
		paramList[13] = paper.getParam14();
		paramList[14] = paper.getParam15();
		paramList[15] = paper.getParam16();
		paramList[16] = paper.getParam17();
		paramList[17] = paper.getParam18();
		paramList[18] = paper.getParam19();
		paramList[19] = paper.getParam20();
		paramList[20] = paper.getParam21();
		paramList[21] = paper.getParam22();
		paramList[22] = paper.getParam23();
		paramList[23] = paper.getParam24();
		paramList[24] = paper.getParam25();			
		return paramList;		
	}
	
	private List<Paper> sortPaperList(List<Paper> paperList){
		List<Paper> newPaperList = new ArrayList<Paper>();
		int n = 0;
		if(paperList != null && paperList.size() > 0){
			for (int i = 0; i < paperList.size(); i++) {
				if(n == paperList.get(i).getExcelorder()){
					if(n < paperList.size()){						
						newPaperList.add(paperList.get(i));
						n++;
						continue;
					}else{
						break;
					}
				}
			}
		}		
		return newPaperList;		
	}
}
