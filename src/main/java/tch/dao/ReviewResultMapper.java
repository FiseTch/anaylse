package tch.dao;

import java.util.List;

import tch.model.ReviewResult;

public interface ReviewResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReviewResult record);

    int insertSelective(ReviewResult record);

    ReviewResult getRevByPrimaryKey(String id);
    
    List<ReviewResult> getRevByAttr(ReviewResult reviewResult);
    
    List<ReviewResult> getGeneralRevByAttr(ReviewResult reviewResult);

    int updateByPrimaryKeySelective(ReviewResult record);

    int updateByPrimaryKey(ReviewResult record);
}