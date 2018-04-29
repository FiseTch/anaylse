package tch.impl;

import java.util.List;

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

	/**
	 * 通过主键id删除记录
	 */
	public int deleteById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入记录（属性不允许为空）
	 */
	public int insertReviewResult(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.insert(reviewResult);
	}

	/**
	 * 插入记录（除主键外，其余的属性允许为空）
	 */
	public int insertReviewResultSelective(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.insertSelective(reviewResult);
	}

	/**
	 * 通过id查询记录
	 */
	public ReviewResult getRevById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.getRevByPrimaryKey(id);
	}

	/**
	 * 根据主键id更新记录（其余属性允许为空）
	 */
	public int updateByIdSelective(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.updateByPrimaryKeySelective(reviewResult);
	}

	/**
	 * 根据主键id更新记录（属性不允许为空）
	 */
	public int updateById(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.updateByPrimaryKey(reviewResult);
	}

	/**
	 * 根据属性值查记录
	 */
	public List<ReviewResult> getRevByAttr(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.getRevByAttr(reviewResult);
	}

	@Override
	public List<ReviewResult> getGeneralRevByAttr(ReviewResult reviewResult) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return reviewResultMapper.getGeneralRevByAttr(reviewResult);
	}
	
	
}
