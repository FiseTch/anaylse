package tch.dao;

import java.util.List;

import tch.model.ReviewResult;

public interface ReviewResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReviewResult reviewResult);

    int insertSelective(ReviewResult reviewResult);

   ReviewResult getRevByPrimaryKey(String id);
    
    List<ReviewResult> getRevByAttr(ReviewResult reviewResult);

    int updateByPrimaryKeySelective(ReviewResult reviewResult);

    int updateByPrimaryKey(ReviewResult reviewResult);
}