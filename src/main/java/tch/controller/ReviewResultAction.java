package tch.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tch.model.Paper;
import tch.model.PaperDetail;
import tch.model.PaperDetailToString;
import tch.model.ReviewResult;
import tch.model.ReviewResultToString;
import tch.service.IPaperDetailService;
import tch.service.IPaperService;
import tch.service.IReviewResultService;
import tch.util.CalcUtil;
import tch.util.ConstantTch;
import tch.util.MyCommonUtil;

@Controller
@RequestMapping("/reviewResult")
public class ReviewResultAction {
	
	private static Log log = LogFactory.getLog(ReviewResultAction.class);
	
	@Resource
	private IReviewResultService reviewResultService;
	@Resource
	private IPaperService paperService;
	@Resource
	private IPaperDetailService paperDetailService;
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: dealData
	 * @Description: 分析数据得到结果
	 * @param paperId
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/dealData")
	public  ModelAndView dealData(@RequestParam("paperId")String paperId,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView model = new ModelAndView();
		paperId = MyCommonUtil.changeEncode(paperId);
		Paper paper = new Paper();
		paper.setPaperid(paperId);
		PaperDetail paperDetail = paperDetailService.getPaperDetailById(paperId);
		List<Paper> paperList = paperService.getPaperByAttr(paper);
		int score = paperDetail.getScore();//试卷总分
		int totalNum =paperDetail.getTotaltitle();//题目数量
		Map<Integer,List<Integer>> data = getAllData(paperList);//按excel的方式存储数据。每行的分数，去除了第一行，去除了第一列，
		Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();//以每道题存储数据
		int totalNo = paperList.size();//excel总行，测验总人数-1	
		for (int i = 0; i <= totalNum; i++) { //每一行中第二个位置开始，即是第一题，随后到总分
			List<Integer> totalGrade = new ArrayList<Integer>();
			for (int j = 1; j < totalNo; j++) {//map从0开始存
				totalGrade.add(data.get(j).get(i));//循环得到每一道题目的成绩
			}
			map.put(i, totalGrade);
		}
		try {
			//信度计算
			double validity = CalcUtil.calcValidity2(totalNum, totalNo-1, map);
			//难度计算
			double difficulty = CalcUtil.calcDifficulty(totalNum, score, map);//试卷总分？
			//效度计算.设置一个标准校测的list（分数控制到75）
			double reliability = CalcUtil.calcReliability(map.get(totalNum), ConstantTch.setStandardList(totalNo-1),totalNo-1);
			//区分度计算
			double distinction = CalcUtil.calcDistinction(map.get(totalNum), score);
			
			String reviewResultId = saveDataToRev(validity, difficulty, reliability, distinction, session,paperId);
			
			if(reviewResultId != null){			
				model = getReviewReesultById(reviewResultId);											
			}else{
				model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
				model.setViewName("/view/result/uploadFailure");
			}
		} catch (Exception e) {	
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName());
			model.setViewName("/view/result/uploadFailure");
			e.printStackTrace();
		}
		return model;
		
	}
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getReviewResult
	 * @Description: 查询结果表reviewResult，取得所有的结果
	 * @param session
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping("/getReviewResult")
	public ModelAndView getReviewResult(HttpSession session){
		ModelAndView model = new ModelAndView();
		List<ReviewResult>  reviewList = null;
		List<ReviewResultToString> reviewResultList = null;
		ReviewResult reviewResult = new ReviewResult();
		String userId = MyCommonUtil.getUserId(session);
		if(null != userId){
			reviewResult.settId(userId);
			reviewList = reviewResultService.getRevByAttr(reviewResult);
			reviewResultList = convertReview(reviewList);
			if(null != reviewResultList && reviewResultList.size() >0){
				model.addObject("flag", true);
				model.addObject("reviewResultList", reviewResultList);
			}else{
				model.addObject("flag",false);
			}
			model.addObject("flag1",true);
			model.setViewName("calcResult");
		}else{
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName()+"用户id为空");
			model.addObject("logMsg", "用户id为空"+Thread.currentThread().getStackTrace()[1].getMethodName());
			model.setViewName("view/result/uploadFailure");
		}
		
		return model;
		
	}
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: selectReviewResult
	 * @Description: 模糊查询
	 * @param keyWord
	 * @param status 用来标识是否传递reviewResultId，当status 为true 标识传递reviewResultId，否则传递 false
	 * @param session
	 * @return
	 * @return: ModelAndView
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/selectReviewResult",method = RequestMethod.POST)
	public ModelAndView selectReviewResult(@RequestParam("keyWord")String keyWord,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView model = new ModelAndView();
		keyWord = MyCommonUtil.changeEncode(keyWord);
		if(keyWord == null){
			model = getReviewResult(session);
		}else{
			ReviewResult reviewResult = new ReviewResult();
			reviewResult.setpId(keyWord);
			String userId = MyCommonUtil.getUserId(session);
			if(null != userId){
				reviewResult.settId(userId);
				List<ReviewResult> reviewResultList = reviewResultService.getGeneralRevByAttr(reviewResult);		
				if(null != reviewResultList && reviewResultList.size() > 0){				
					List<ReviewResultToString> reviewResultListToString = convertReview(reviewResultList);		
					model.addObject("reviewResultList", reviewResultListToString);
					model.addObject("flag1",true);
				}else{
					model.addObject("flag1",false);
				}
				model.addObject("flag",true);
				model.setViewName("calcResult");
			}else{
				model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName()+"用户id为空");
				model.setViewName("view/result/uploadFailure");
			}
		}
		return model;
				
		
	}
	
	private ModelAndView getReviewReesultById(String reviewResultId){
		ModelAndView model = new ModelAndView();
		ReviewResult reviewResult = reviewResultService.getRevById(reviewResultId);
		if(reviewResult != null){
			List<ReviewResult> reviewResultList = new ArrayList<ReviewResult>();
			reviewResultList.add(reviewResult);
			List<ReviewResultToString> reviewResultListToString = convertReview(reviewResultList);
			model.addObject("reviewResultList", reviewResultListToString);
			model.addObject("flag1",true);
			model.addObject("flag",true);
			model.setViewName("calcResult");
		}else{ 
			model.addObject("errorMsg", "当前页面为"+Thread.currentThread().getStackTrace()[1].getClassName()+"用户id为空");
			model.setViewName("view/result/uploadFailure");
		}
		return model;
		
	}
	
	private List<ReviewResultToString> convertReview(List<ReviewResult> reviewList){
		List<ReviewResultToString> reviewListToString = new ArrayList<ReviewResultToString>();
		if(null != reviewList && reviewList.size() > 0 ){
			for (int i = 0; i < reviewList.size(); i++) {
				ReviewResultToString reviewToString = new ReviewResultToString();
				
				//将Date换成String
				reviewToString.setTime(
						 MyCommonUtil.databaseToWeb(reviewList.get(i).getTime(),"yyyy-MM-dd HH:mm:ss"));
				
				reviewToString.setDifficulty(reviewList.get(i).getDifficulty());
				reviewToString.setDistinction(reviewList.get(i).getDistinction());
				reviewToString.setId(reviewList.get(i).getId());
				reviewToString.setpId(reviewList.get(i).getpId());
				reviewToString.setReliability(reviewList.get(i).getReliability());
				reviewToString.settId(reviewList.get(i).gettId());
				reviewToString.setValidityA(reviewList.get(i).getValidityA());
				reviewToString.setValidityB(reviewList.get(i).getValidityB());	
				reviewListToString.add(reviewToString);
			}
			
		}
		return (null != reviewListToString && reviewListToString.size() > 0) ? reviewListToString : null;		
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
	private String saveDataToRev(Double validity,Double difficulty,
			Double reliability,Double distinction,HttpSession session,String paperId) throws Exception{
		int i = 0;
		ReviewResult rev = new ReviewResult();
		String teacherId = MyCommonUtil.getUserId(session);
		if (null != teacherId && null != paperId) {	
			rev.setId(MyCommonUtil.getTimeString());//得到唯一id
			rev.setpId(paperId);
			rev.settId(teacherId);
			rev.setValidityB(validity);
			rev.setDifficulty(difficulty);
			rev.setReliability(reliability);
			rev.setDistinction(distinction);
			i = reviewResultService.insertReviewResultSelective(rev);
		}else{
			throw new Exception("用户id或者试卷id为空");
		}
		return (i == 1)?rev.getId():null;
		
	}
	
	private Map<Integer,List<Integer>> getAllData(List<Paper> paperList){
		Map<Integer,List<Integer>> data= new HashMap<Integer,List<Integer>>();
		if(paperList != null && paperList.size() > 0){			
			for(int i = 0; i<paperList.size(); i++){
				if(paperList.get(i).getExcelorder() != 0){
					String[] paramList = convertToString(paperList.get(i));
					List<Integer> rowList = new ArrayList<Integer>();
					for (int j = 1; j < paperList.get(i).getNum(); j++) {//从第一列开始
						rowList.add(Integer.parseInt(paramList[j]));
					}
					data.put(i,rowList);
				}
			}
		}
		return data;		
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
}
