package tch.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import tch.util.CalcUtil;
import tch.util.ConstantTch;

@Controller
@RequestMapping("/anaylse")

public class Anaylse {
	
	private static Log log = LogFactory.getLog(Anaylse.class);
	@SuppressWarnings("unchecked")
	@RequestMapping("/dealData")
	//@RequestParam("data")String data
	public static ModelAndView dealData(Map<Integer,List<String>> data,HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		data = (Map<Integer, List<String>>) request.getAttribute("data");
		Map<Integer,List<Integer>> map = new HashMap<Integer, List<Integer>>();//
		//List<String> totalGrade = new ArrayList<String>();//试卷总分list
		int totalNo = Integer.parseInt(data.get(0).get(0));//excel总行，测验总人数-1	
		int totalNum = data.get(1).size();//excel总列，测验题目数-2
		for (int i = 1; i < totalNum; i++) { //每一行中第二个位置开始，即是第一题，随后到总分
			List<Integer> totalGrade = null;
			for (int j = 1; j < totalNo; j++) {
				totalGrade = new ArrayList<Integer>();
				totalGrade.add(Integer.parseInt(data.get(j).get(i)));//循环得到每一道题目的成绩
				map.put(i -1, totalGrade);
			}
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
			model.addObject("result",resultMap);
			model.setViewName("/result/showResult");
		} catch (Exception e) {	
			log.error(e);
			e.printStackTrace();
		}
		return model;
		
	}
}
