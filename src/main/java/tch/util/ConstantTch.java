package tch.util;

import java.util.ArrayList;
import java.util.List;

import tch.model.User;

public class ConstantTch {
	
	//默认User
	public static final User DEFAULT_USER = getUser();
	public static final String USERID = "userId_tch";//用户id存取的变量名
	//excel下载时文件格式
	public static final String FILEEXTENSION = ".xls";//文件下载时的格式控制
	public static final String DOWNFILENAME = "试卷分析结果";//Excel下载时的名称
	public static final String DOWNEXPORTFILENAME = "上传成绩表模板";//excel模板下载时的名称
	//分析结果下载时的表头数据
	public static final String RESULT_ID = "结果编号";
	public static final String RESULT_PID = "试卷编号";
	public static final String RESULT_TID = "用户编号";
	public static final String RESULT_TIME = "结果分析时间";
	public static final String RESULT_VALIDITY = "信度";
	public static final String RESULT_DIFFICULTY = "难度";
	public static final String RESULT_DISTINCTION = "区分度";
	public static final String RESULT_RELIABILITY = "校标度";
	//模板下载时表头数据
	public static final String STUNO = "学号";
	public static final String TOTAL = "总分";
	public static final String ORDER = "第";
	public static final String QUESTION = "题得分"; 
	
	//试卷总分、
//	public static final int TOTALGRADE = 100;
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
	
	private static  User getUser(){
		User user = new User();
		user.setUsername("default");
		user.setPassword("default");
		return user;
	}
	
	public static List<Integer> setStandardList(int size){
		list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(75);//以75分为标准分
		}
		return null;
		
	}
}
