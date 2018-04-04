package tch.service;

import tch.model.Teacher;

public interface ITeacherService {
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: deleteById
	 * @Description: 通过主键id删除记录
	 * @param id
	 * @return
	 * @return: int
	 */
    public int deleteById(String id);
    
    /**
     * 
     * @user: tongchaohua
     * @Title: insertTeacher
     * @Description: 插入一条记录（属性不允许为空）
     * @param teacher
     * @return
     * @return: int
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertTeacherSelective
     * @Description: 插入一条记录（属性允许为空）
     * @param teacher
     * @return
     * @return: int
     */
    public int insertTeacherSelective(Teacher teacher);

    /**
     * 
     * @user: tongchaohua
     * @Title: selectById
     * @Description: 通过主键id查询记录
     * @param id
     * @return
     * @return: Teacher
     */
    public Teacher selectById(String id);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateByIdSelective
     * @Description: 更新记录（属性允许为空除主键外）
     * @param teacher
     * @return
     * @return: int
     */
    public int updateByIdSelective(Teacher teacher);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateById
     * @Description: 更新记录（属性不允许为空）
     * @param teacher
     * @return
     * @return: int
     */
    public int updateById(Teacher teacher);
}
