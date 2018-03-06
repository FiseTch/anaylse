package tch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tch.dao.ReviewResultMapper;
import tch.model.ReviewResult;

@Service("reviewResultService")
public class ReviewResultServiceImpl implements ReviewResultMapper {

	@Autowired
	private ReviewResultMapper reviewResultMapper;
	
	public ReviewResultMapper getReviewResultMapper() {
		return reviewResultMapper;
	}

	public void setReviewResultMapper(ReviewResultMapper reviewResultMapper) {
		this.reviewResultMapper = reviewResultMapper;
	}

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(ReviewResult record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(ReviewResult record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ReviewResult selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(ReviewResult record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(ReviewResult record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
