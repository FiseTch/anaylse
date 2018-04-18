package tch.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tch.model.ReviewResult;
import tch.service.IReviewResultService;
import tch.util.CalcUtil;
import tch.util.ConstantTch;

@Controller
@RequestMapping(value = "/calc")
public class CalcAction {
	
	private static Log log = LogFactory.getLog(CalcAction.class);
	
	@Resource
	private IReviewResultService reviewResultService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dealData")
	public  ModelAndView dealData(Map<Integer,List<String>> data,HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		ModelAndView model = new ModelAndView();
		data = (Map<Integer, List<String>>) session.getAttribute("data");
		Map<Integer,List<Integer>> map = new HashMap<Integer, List<Integer>>();//
		//List<String> totalGrade = new ArrayList<String>();//试卷总分list
		int totalNo = Integer.parseInt(data.get(0).get(0));//excel总行，测验总人数-1	
		int totalNum = data.get(1).size();//excel总列，测验题目数-2
		for (int i = 1; i < totalNum; i++) { //每一行中第二个位置开始，即是第一题，随后到总分
			List<Integer> totalGrade = new ArrayList<Integer>();
			for (int j = 2; j <= totalNo; j++) {//map中第3个位置开始才是分数
				totalGrade.add(Integer.parseInt(data.get(j).get(i)));//循环得到每一道题目的成绩
			}
			map.put(i -1, totalGrade);
		}
		try {
			double validity = CalcUtil.calcValidity2(totalNum-2, totalNo-1, map);
			double difficulty = CalcUtil.calcDifficulty(totalNum-2, ConstantTch.TOTALGRADE, map);//试卷总分？
			double reliability = CalcUtil.calcReliability(map.get(totalNum-2), ConstantTch.setStandardList(totalNum-2));
			double distinction = CalcUtil.calcDistinction(map.get(totalNum-2), ConstantTch.TOTALGRADE);
			Map<String,Double> resultMap = new HashMap<String, Double>();
			resultMap.put(ConstantTch.VALIDITY, validity);
			resultMap.put(ConstantTch.DIFFICULTY, difficulty);
			resultMap.put(ConstantTch.RELIABILITY, reliability);
			resultMap.put(ConstantTch.DISTINCTION, distinction);
			boolean b = saveDataToRev(resultMap,session);
			if(b){
				model.addObject("saveMsg","存入数据库成功");
			}else{
				model.addObject("saveMsg","存入数据库失败");
			}
			model.addObject("result",resultMap);
/*			session.setAttribute("resultMap", resultMap);*/
			model.setViewName("/view/result/showResult");
		} catch (Exception e) {	
			log.error(e);
			model.setViewName("/view/result/uploadFailure");
			e.printStackTrace();
		}
		return model;
		
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: saveDataToRev
	 * @Description: 将分析结果存入表中reviewResult
	 * @param resultMap
	 * @param session
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	private boolean saveDataToRev(Map<String,Double> resultMap,HttpSession session) throws Exception{
		int i = 0;
		ReviewResult rev = new ReviewResult();
		String teacherId = (String) session.getAttribute("userId");
		String paperId = (String) session.getAttribute("paperId");
		if (null != teacherId && null != paperId) {			
			rev.setpId(teacherId);
			rev.settId(paperId);
			rev.setValidityB(resultMap.get(ConstantTch.VALIDITY));
			rev.setDifficulty(resultMap.get(ConstantTch.DIFFICULTY));
			rev.setReliability(resultMap.get(ConstantTch.RELIABILITY));
			rev.setDistinction(resultMap.get(ConstantTch.DISTINCTION));
			i = reviewResultService.insertReviewResultSelective(rev);
		}else{
			throw new Exception("用户id或者试卷id为空");
		}
		return (i == 1)?true:false;
		
	}
}
