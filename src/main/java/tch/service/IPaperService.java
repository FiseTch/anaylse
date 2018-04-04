package tch.service;

import tch.model.Paper;

public interface IPaperService {
	
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

    /**
     * 
     * @user: tongchaohua
     * @Title: insertPaper
     * @Description: 插入记录（属性不允许为空）
     * @param paper
     * @return
     * @return: int
     */    
    public int insertPaper(Paper paper);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertPaperSelective
     * @Description: 插入记录（除主键外。其他属性允许为空）
     * @param paper
     * @return
     * @return: int
     */
    public int insertPaperSelective(Paper paper);

    /**
     * 
     * @user: tongchaohua
     * @Title: selectById
     * @Description: 根据主键查询记录
     * @param id
     * @return
     * @return: Paper
     */
    public Paper selectById(String id);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateByIdSelective
     * @Description: 更新记录（除主键外。其他属性允许为空）
     * @param paper
     * @return
     * @return: int
     */
    public int updateByIdSelective(Paper paper);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateById
     * @Description: 更新记录（属性不允许为空）
     * @param paper
     * @return
     * @return: int
     */
    public int updateById(Paper paper);
}
