package tch.service;


import java.util.List;

import tch.model.ReviewResult;

public interface IReviewResultService {
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: selectById
	 * @Description: 根据主键查询记录
	 * @param id
	 * @return
	 * @return: ReviewResult
	 */
	public ReviewResult getRevById(String id);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getRevByAttr
	 * @Description: 通过属性查询记录
	 * @param reviewResult
	 * @return
	 * @return: ReviewResult
	 */
	public List<ReviewResult> getRevByAttr(ReviewResult reviewResult);

	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getGeneralRevByAttr
	 * @Description: 模糊查询
	 * @param reviewResult
	 * @return
	 * @return: List<ReviewResult>
	 */
	public List<ReviewResult> getGeneralRevByAttr(ReviewResult reviewResult);
	
    /**
     * 
     * @user: tongchaohua
     * @Title: ReviewResult
     * @Description: 插入记录（属性不允许为空）
     * @param reviewResult
     * @return
     * @return: int
     */
    public int insertReviewResult(ReviewResult reviewResult);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertReviewResultSelective
     * @Description: 插入记录（除主键外。其他属性允许为空）
     * @param reviewResult
     * @return
     * @return: int
     */  
    public int insertReviewResultSelective(ReviewResult reviewResult);


    /**
     * 
     * @user: tongchaohua
     * @Title: updateByIdSelective
     * @Description: 更新记录（除主键外。其他属性允许为空）
     * @param reviewResult
     * @return
     * @return: int
     */
    public int updateByIdSelective(ReviewResult reviewResult);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateById
     * @Description:  更新记录（属性不允许为空）
     * @param reviewResult
     * @return
     * @return: int
     */
    public int updateById(ReviewResult reviewResult);
    
    /**
     * 
     * @user: tongchaohua
     * @Title: deleteById
     * @Description: 删除记录
     * @param id
     * @return
     * @return: int
     */
    public int deleteById(String id);
}
