package tch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tch.util.CalcUtil;

/**
 * 
 * 
 * Copyright:tch
 * 
 * @class: tch.controller
 * @Description: 信度计算
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
@RequestMapping("/validity")//访问路径
public class Validity {
	private static Log log = LogFactory.getLog(Validity.class);
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcValidity
	 * @Description: 重测系数法与折半法计算信度
	 * @param oddList奇数题目的总分
	 * @param evenList偶数题目的总分
	 * @return: void
	 * @throws Exception 
	 */
	@RequestMapping("/calcValidity")//访问路径
	public double calcValidity(List<Integer> oddList,List<Integer> evenList) throws Exception{
		
		double calcDeviation = CalcUtil.calcDeviation(oddList, evenList);
		double oddVariance = CalcUtil.calcVariance(oddList);
		double evenVariance = CalcUtil.calcVariance(evenList);
		//若执行到这一步则证明List不为空
		if (oddVariance == -1 && evenVariance == -1) {			
			return -1;
		}else{
			if (oddVariance != 0 && evenVariance != 0) {
				double r =  evenVariance/(oddList.size()*oddVariance*evenVariance);
				return 2*r/(r+1);
			}else{
				return 0;
			}
		}		
	}
/**
 * 
 * @user: tongchaohua
 * @Title: calcValidity2
 * @Description: 克伦巴赫系数法
 * @param totalNum 试卷总题目数
 * @param totalNo 测验人数
 * @param tch1 试卷总分list
 * @param tch2 各题总分map
 * @return
 * @return: double
 * @throws Exception 
 */
	public double calcValidity2(int totalNum,int totalNo, List<Integer> tch1,Map<Integer,List<Integer>> tch2) throws Exception{
		int sum = 0 ;
		for (int i = 0; i < totalNo; i++) {
			List<Integer> tch3 = new ArrayList<Integer>();
			tch3 = tch2.get(String.valueOf(i));
			double calcStandard4One = CalcUtil.calcVariance(tch3);//计算每一道题目的标准差
			if (calcStandard4One == -1) {
				throw new Exception("计算第"+i+"位同学的标准差出错！！！");
			}
			sum += calcStandard4One;	
		}
		double calcStandard4All = CalcUtil.calcVariance(tch1);//计算试卷的方差
		if (calcStandard4All == -1) {
			throw new Exception("计算试卷方差出错！！！");
		}		
		return totalNum/(totalNum -1)*(1 - sum/calcStandard4All);		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: checkValidity
	 * @Description: 对信度的值进行分析
	 * 信度一般要求大于 0.5 。影响信度的因素很多。如难度过高，降低信度；试卷题量(测量长度)过少，信度降低
	 * @param validity 信度
	 * @return: boolean
	 */
	public boolean checkValidity(double validity){
		return (validity < 0.5) ? true :false;		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: updatevalidity
	 * @Description: 通过调整题目数对信度的值进行调整
	 * @param totalNum 原来总的题目数
	 * @param factValidity 实际信度
	 * @param hopeValidity 希望信度
	 * @return: void
	 */
	public int updatevalidity(int totalNum,double factValidity,double hopeValidity){
		if ((1-hopeValidity)*factValidity != 0) {			
			return  (int) (((1-factValidity)*hopeValidity)/((1-hopeValidity)*factValidity));
		}else{
			return -1;
		}		
	}
	
}
