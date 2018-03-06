package tch.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tch.util.CalcUtil;
import tch.util.ConstantTch;

/**
 * 
 * 
 * Copyright:
 * 
 * @class: tch.controller
 * @Description: 难度计算
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
@RequestMapping("/difficulty")
public class Difficulty {
	private static Log log = LogFactory.getLog(Difficulty.class);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcDifficulty
	 * @Description: TODO
	 * @param grade -每道题目的分数列表：计算总分与平均分 
	 * @return pi难度系数 p_i=x i/y_i 
	 * @return: double
	 */
	public double calcSingleDifficulty(List<Integer> grade){
		
		double avg = CalcUtil.calcAvg(grade);
		double sum = CalcUtil.calcSum(grade);
		
		return (sum != 0)? (avg/sum) : 0;	
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcDifficulty
	 * @Description: TODO
	 * @param totalNum 全卷的总题目数
	 * @param totalGrade 试卷总分
	 * @param grade 全体学生每道题目的得分，两维数组 ,以题目为key
	 * @return 试卷难度
	 * @return: double
	 */
	public double calcDifficulty(int totalNum, int totalGrade,Map<Integer,List<Integer>> grade ){
		double sum  = 0;
		for (int i = 0; i < totalNum; i++) {
			//calcSingleDifficulty(grade.get(i));//计算每道题目的pi*yi = 计算每道题目的平均分相加即可
			sum += CalcUtil.calcAvg(grade.get(i));
		}
		return (sum != 0) ? (sum/totalGrade) : 0;
		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: checkDifficulty
	 * @Description: 对难度进行校验
	 * @param difficulty
	 * @return 
	 * @return: String
	 */
	public String checkDifficulty(double difficulty){
		if (difficulty < 0.8 && difficulty > 0.6) {
			return ConstantTch.difficulty_A;
		}else{			
			return ConstantTch.difficulty_B;
		}
	
	}
}
