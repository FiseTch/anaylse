package tch.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tch.dao.ReviewResultMapper;
import tch.model.ReviewResult;
import tch.service.IReviewResultService;

/**
 * 
 * 
 * Copyright:tch
 * 
 * @class: tch.impl
 * @Description: 
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-04-05
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-04-05     tongch          v1.1.0
 */
@Service("reviewResultService")
@Scope("prototype")
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
