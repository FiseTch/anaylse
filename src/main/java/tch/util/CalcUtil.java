package tch.util;

import java.util.Iterator;
import java.util.List;

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
}
