package tch.dao;

import tch.model.ReviewResult;

public interface ReviewResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReviewResult reviewResult);

    int insertSelective(ReviewResult reviewResult);

    ReviewResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReviewResult reviewResult);

    int updateByPrimaryKey(ReviewResult reviewResult);
}