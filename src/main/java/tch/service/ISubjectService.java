package tch.service;

import tch.model.Subject;

public interface ISubjectService {
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: deleteById
	 * @Description: 通过主键id删除用户
	 * @param id
	 * @return
	 * @return: int
	 */
    public int deleteById(Integer id);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertSubject
     * @Description: 插入记录（不允许为空）
     * @param subject
     * @return
     * @return: int
     */
    public int insertSubject(Subject subject);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertSubjectSelective
     * @Description: 插入记录（除主键外，其他属性不允许为空）
     * @param subject
     * @return
     * @return: int
     */
    public int insertSubjectSelective(Subject subject);

    /**
     * 
     * @user: tongchaohua
     * @Title: selectById
     * @Description: 通过抓紧id查询记录
     * @param id
     * @return
     * @return: Subject
     */
    public Subject selectById(Integer id);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateByIdSelective
     * @Description: 更新记录（除主键外，其他属性允许为空）
     * @param subject
     * @return
     * @return: int
     */
    public int updateByIdSelective(Subject subject);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateById
     * @Description: 更新记录（属性不允许为空）
     * @param subject
     * @return
     * @return: int
     */
    public int updateById(Subject subject);

}
