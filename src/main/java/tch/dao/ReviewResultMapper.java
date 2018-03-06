package tch.dao;

import tch.model.ReviewResult;

public interface ReviewResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReviewResult record);

    int insertSelective(ReviewResult record);

    ReviewResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReviewResult record);

    int updateByPrimaryKey(ReviewResult record);
}