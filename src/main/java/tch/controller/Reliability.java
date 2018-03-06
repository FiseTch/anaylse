package tch.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tch.util.CalcUtil;

/**
 * 
 * 
 * Copyright:
 * 
 * @class: tch.controller
 * @Description:效度计算 ,--校标相关效度
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-02-04
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-02-04     tongch          v1.1.0
 */
@Controller
@RequestMapping("/reliability")
public class Reliability {	
	private static Log log = LogFactory.getLog(Reliability.class);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcReliability
	 * @Description: TODO
	 * @param factList 实际的分数
	 * @param standardList  校准的分数列表
	 * @return: void
	 * @throws Exception 
	 */
	@RequestMapping("/calcReliability")
	public double calcReliability(List<Integer> factList, List<Integer> standardList) throws Exception{
		double factAvg = CalcUtil.calcDeviation(factList, standardList);
		
		double factVariance =  CalcUtil.calcVariance(factList);		
		double standardVariance = CalcUtil.calcStandard(standardList);
		
		return factAvg/(factList.size()*factVariance*standardVariance);
		
		
		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcReliability
	 * @Description: TODO
	 * @param factList  实际分数
	 * @param avg  校准均值 ---由历年均值确定，或者方差评定
	 * @param standard  校准方差 ---由历年均值确定，或者方差评定
	 * @return: void
	 */
	public void calcReliability(List<Integer> factList,double avg,double standard){
		//无法评定
	}
}
