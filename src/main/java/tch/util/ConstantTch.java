package tch.util;

import java.util.ArrayList;
import java.util.List;

public class ConstantTch {
	//评价指标
	public static final String VALIDITY = "信度";
	public static final String DIFFICULTY = "难度";
	public static final String DISTINCTION = "区分度";
	public static final String RELIABILITY = "校标度";
	//试卷总分、
	public static final int TOTALGRADE = 100;
	//设置一个标准的list
	public static List<Integer> list = null;
	
	public static final String VALIDITY_A = "信度适中";//>0.5 
	public static final String VALIDITY_B = "信度需调整";
	public static final String DIFFICULTY_A = "难度适中"; //0.6-0.8能客观反映试卷难度
	public static final String DIFFICULTY_B = "难度设置不太合理";
	public static final String DISTINCTION_A = "质量非常好";//>0.4
	public static final String DISTINCTION_B = "良好，如能改进更好";//0.3 -0.4
	public static final String DISTINCTION_C = "尚可，仍需要改进";//0.2-0.3
	public static final String DISTINCTION_D = "很差，必须淘汰或加以修改";//>0.2
	public static final String RELIABILITY_A = "测验完全反映了校标"	;//==1	
	public static final String RELIABILITY_B = "测验完全不符合校标";//==0
	public static final String RELIABILITY_C = "校标度适中";//0.4-0.7
	public static final String RELIABILITY_D = "校标度不合理";//其他 
	
	public static List<Integer> setStandardList(int size){
		list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(75);//以75分为标准分
		}
		return null;
		
	}
}
