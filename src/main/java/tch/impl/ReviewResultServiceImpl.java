package tch.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import tch.dao.ReviewResultMapper;
import tch.model.ReviewResult;
import tch.service.IReviewResultService;

@Service("reviewResultService")
public class ReviewResultServiceImpl implements IReviewResultService {

	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource
	private ReviewResultMapper reviewResultMapper;

	public int deleteById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.deleteByPrimaryKey(id);
	}

	public int insertReviewResult(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.insert(reviewResult);
	}

	public int insertReviewResultSelective(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.insertSelective(reviewResult);
	}

	public ReviewResult selectById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.selectByPrimaryKey(id);
	}

	public int updateByIdSelective(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.updateByPrimaryKeySelective(reviewResult);
	}

	public int updateById(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.updateByPrimaryKey(reviewResult);
	}
	
	
}
