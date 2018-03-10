package tch.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 
 * Copyright:
 * 
 * @class: tch.util
 * @Description: 计算方差和标准差
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-02-04
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-02-04     tongch          v1.1.0
 */
public class CalcUtil {

	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcAvg
	 * @Description: 计算均值
	 * @param tch
	 * @return
	 * @return: double
	 */
	public static double calcAvg(List<Integer> tch) {
		if (null != tch && tch.size() > 0) {
			int sum = 0;
			for (Integer elem : tch) {
				sum += elem;
			}
			return sum/tch.size();			
		}else{
			return -1;
		}
		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcVariance
	 * @Description: 计算方差
	 * @param tch
	 * @return
	 * @return: double
	 */
	public static double calcVariance(List<Integer> tch){
		if (null != tch && tch.size() > 0) {
			int sum = 0;
			double temp = 0;
			double avg = 0;
			
			for (Integer elem : tch) {
				sum += elem;
			}
			avg = sum/tch.size();//得到平均值
			for (int i = 0; i < tch.size(); i++) {
				temp += Math.pow((tch.get(i)-avg), 2);
			}
			return temp/tch.size();
		}else{
			return -1;
		}		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcStandard
	 * @Description: 求标准差
	 * @param tch
	 * @return
	 * @return: double
	 */
	public static double calcStandard(List<Integer> tch){
		double value = calcVariance(tch);		
		return (value == -1) ? -1 : Math.sqrt(value);
		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcDeviation
	 * @Description: 计算（x-X）（y-Y)
	 * @param tch1
	 * @param tch2
	 * @return
	 * @throws Exception
	 * @return: double
	 */
	public static double calcDeviation(List<Integer> tch1,List<Integer> tch2) throws Exception{
		if (tch1 != null && tch1.size() > 0 && tch2 != null && tch2.size() > 0) {			
			if (tch1.size() != tch2.size()) {
				throw new Exception("两组测试数据不相等，无法进行计算");
			}
			double sum = 0 ;
			double tch1Avg = calcAvg(tch1);
			double tch2Avg = calcAvg(tch2);
			for (int i = 0; i < tch1.size(); i++) {
				sum += (tch1.get(i)-tch1Avg)*(tch2.get(i)-tch2Avg);
			}
			return sum;
		}else{
			return -1;
		}
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: sum
	 * @Description: TODO
	 * @param grade
	 * @return 计算每道题的总分
	 * @return: int
	 */
	public static int calcSum(List<Integer> grade) {
		int sum = 0;
		if (grade != null && grade.size() >0 ) {
			for (int i = 0; i < grade.size(); i++) {
				sum += grade.get(i);
			}
		}
		return sum;
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: sortList
	 * @Description: 对数组元素降序排序
	 * @param grade
	 * @return
	 * @return: List<Integer>
	 */
	public static List<Integer> sortList(List<Integer> grade){		
		if (null != grade && grade.size() > 0)  {
			Collections.sort(grade);//升序排列
			Collections.reverse(grade);//反转list
			return grade;
		}else{
			return null;
		}
		
		
		
	}
	//Validity 信度计算 Difficulty 难度计算 Distinction 区分度计算 Reliability 效度计算 ,--校标相关效度
	
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcValidity
	 * @Description: 重测系数法与折半法计算信度
	 * @param oddList奇数题目的总分
	 * @param evenList偶数题目的总分
	 * @return: double
	 * @throws Exception 
	 * 
	 */
	public static double calcValidity(List<Integer> oddList,List<Integer> evenList) throws Exception{
		
		double calcDeviation = calcDeviation(oddList, evenList);
		double oddVariance = calcVariance(oddList);
		double evenVariance = calcVariance(evenList);
		//若执行到这一步则证明List不为空
		if (oddVariance == -1 && evenVariance == -1) {			
			return -1;
		}else{
			if (oddVariance != 0 && evenVariance != 0) {
				double r =  calcDeviation/(oddList.size()*oddVariance*evenVariance);
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
	 * @param tch2 各题总分map 试卷总分list
	 * @return
	 * @return: double
	 * @throws Exception 
	 */
	public static double calcValidity2(int totalNum,int totalNo, Map<Integer,List<Integer>> tch2) throws Exception{
		int sum = 0 ;
		for (int i = 0; i < totalNo; i++) {
			List<Integer> tch3 = new ArrayList<Integer>();
			tch3 = tch2.get(String.valueOf(i));
			double calcStandard4One = calcVariance(tch3);//计算每一道题目的标准差
			if (calcStandard4One == -1) {
				throw new Exception("计算第"+i+"位同学的标准差出错！！！");
			}
			sum += calcStandard4One;	
		}
		double calcStandard4All = calcVariance(tch2.get(String.valueOf(totalNum)));//计算试卷的方差
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
	public static String checkValidity(double validity){
		return (validity > 0.5) ? ConstantTch.RELIABILITY_A :ConstantTch.RELIABILITY_B;		
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
	public static int updatevalidity(int totalNum,double factValidity,double hopeValidity){
		if ((1-hopeValidity)*factValidity != 0) {			
			return  (int) (((1-factValidity)*hopeValidity)/((1-hopeValidity)*factValidity));
		}else{
			return -1;
		}		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcDifficulty
	 * @Description: TODO
	 * @param grade -每道题目的分数列表：计算总分与平均分 
	 * @return pi难度系数 p_i=x i/y_i 
	 * @return: double
	 */
	public static double calcSingleDifficulty(List<Integer> grade){
		
		double avg = calcAvg(grade);
		double sum = calcSum(grade);
		
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
	public static double calcDifficulty(int totalNum, int totalGrade,Map<Integer,List<Integer>> grade ){
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
	public static String checkDifficulty(double difficulty){
		if (difficulty < 0.8 && difficulty > 0.6) {
			return ConstantTch.DIFFICULTY_A;
		}else{			
			return ConstantTch.DIFFICULTY_B;
		}
	
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcReliability
	 * @Description: TODO
	 * @param factList 实际的总分
	 * @param standardList  校准的分数列表r_xy=(∑▒(x-x ̅ )(y-y ̅ ) )/(NS_X S_Y )
	 * @return: void
	 * @throws Exception 
	 */
	public static double calcReliability(List<Integer> factList, List<Integer> standardList) throws Exception{
		double factAvg = CalcUtil.calcDeviation(factList, standardList);
		
		double factVariance =  CalcUtil.calcVariance(factList);		
		double standardVariance = CalcUtil.calcStandard(standardList);
		
		return factAvg/(factList.size()*factVariance*standardVariance);
		
		
		
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: checkReliability
	 * @Description: 对信度进行校验
	 * @param reliability
	 * @return
	 * @return: String
	 */
	public static String checkReliability(double reliability){
		if (reliability == 1) {
			return ConstantTch.RELIABILITY_A;
		}else if (reliability > 0.7) {
			return ConstantTch.RELIABILITY_D;
		}else if (reliability > 0.4) {
			return ConstantTch.RELIABILITY_C;
		}else {
			return (reliability == 0) ?ConstantTch.RELIABILITY_B :ConstantTch.RELIABILITY_D;
		}
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: calcDistinction
	 * @Description: 计算试卷区分度鉴别指数法：
	 * 该方法是通过比较高分组和低分组两个极端在同一试题上反应的差异来估计试题区分度。
	 * 通常的做法是：将测验成绩由高到低排序,然后上端取27%作为高分组,下端取取27%作为低分组。
	 * 分别计算第i题高分组学生的平均成绩(x_ih ) ̅ 和低分组学生的平均成绩(x_il ) ̅ 。
	 * 设第i题满分为y_i,第i题区分度为:d_i=((x_ih ) ̅ -(x_il ) ̅)/y_i 
	 * @param grade 当传来的是一道题的总分list时，对应的totalGrade就是当前题目的总分，若是全卷总分，则对应试卷的list
	 * @param totalGrade
	 * @return
	 * @return: double
	 */
	public static double calcDistinction(List<Integer> grade,int totalGrade){
		List<Integer> list = new ArrayList<Integer>();
		list = sortList(grade);//将测验成绩从高到底排序
		if (null != list && list.size() > 0) {
			int n = (int) (list.size() * 0.27);
			double firstAvg = calcAvg(list.subList(0, n));//截取前27%作为高分组
			double lastAvg = calcAvg(list.subList((list.size() - n),list.size()));//截取后27%作为低分组
			return (totalGrade != 0) ? (firstAvg - lastAvg)/totalGrade : 0;
		}
		return 0;		
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: checkDistinction
	 * @Description: 对区分度进行校验
	 * @param d
	 * @return
	 * @return: String
	 */
	public static String checkDistinction(double d){
		if(d >= 0.4){
			return ConstantTch.DISTINCTION_A;
		}else if (d >= 0.3) {
			return ConstantTch.DISTINCTION_B;
		}else if (d >= 0.2) {
			return ConstantTch.DISTINCTION_C;
		}else{
			return ConstantTch.DISTINCTION_D;
		}
		
	}
}
