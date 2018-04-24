package tch.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tch.model.ReviewResult;
import tch.model.ReviewResultToString;
import tch.service.IReviewResultService;
import tch.util.MyCommonUtil;

@Controller
@RequestMapping("/reviewResult")
public class ReviewResultAction {
	
	
	@Resource
	private IReviewResultService reviewResultService;
	
	
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
		String userId = (String) session.getAttribute("userId");
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
			model.setViewName("calcResult");
		}else{
			model.addObject("errorMsg", "用户id为空");
			model.addObject("logMsg", "用户id为空"+Thread.currentThread().getStackTrace()[1].getMethodName());
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
						 MyCommonUtil.formatDate(reviewList.get(i).getTime(),"yy-MM-dd HH:mm:ss"));
				
				reviewToString.setDifficulty(reviewList.get(i).getDifficulty());
				reviewToString.setDistinction(reviewList.get(i).getDistinction());
				reviewToString.setId(reviewList.get(i).getId());
				reviewToString.setpId(reviewList.get(i).getpId());
				reviewToString.setReliability(reviewList.get(i).getReliability());
				reviewToString.settId(reviewList.get(i).gettId());
				reviewToString.setValidityA(reviewList.get(i).getValidityA());
				reviewToString.setValidityB(reviewList.get(i).getValidityB());				
			}
			
		}
		return (null != reviewListToString && reviewListToString.size() > 0) ? reviewListToString : null;		
	}
}
