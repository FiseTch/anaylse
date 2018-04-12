package tch.service;

import tch.model.DegreeStatic;

public interface IDegreeStaticService {
    

	/**
	 * 
	 * @user: tongchaohua
	 * @Title: selectById
	 * @Description: 通过主键id查询记录
	 * @param id
	 * @return
	 * @return: DegreeStatic
	 */
	public DegreeStatic getDegById(Integer id);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getDegByAttr
	 * @Description: 通过属性查询记录
	 * @param degreeStatic
	 * @return
	 * @return: DegreeStatic
	 */
	public DegreeStatic getDegByAttr(DegreeStatic degreeStatic);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: insertDegreeStatic
	 * @Description: 插入一条记录（属性不允许为空）
	 * @param degreeStatic
	 * @return
	 * @return: int
	 */
    public int insertDegreeStatic(DegreeStatic degreeStatic);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertDegreeStaticSelective
     * @Description: 插入一条记录（属性允许为空）
     * @param degreeStatic
     * @return
     * @return: int
     */
    public int insertDegreeStaticSelective(DegreeStatic degreeStatic);


    /**
     * 
     * @user: tongchaohua
     * @Title: updateByIdSelective
     * @Description: 更新记录（属性允许为空除主键外）
     * @param degreeStatic
     * @return
     * @return: int
     */
    public int updateByIdSelective(DegreeStatic degreeStatic);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateById
     * @Description: 更新记录（属性不允许为空）
     * @param degreeStatic
     * @return
     * @return: int
     */
    public int updateById(DegreeStatic degreeStatic);

    /**
     * 
     * @user: tongchaohua
     * @Title: deleteById
     * @Description: 通过主键id删除记录
     * @param id
     * @return
     * @return: int
     */
    public int deleteById(Integer id);
}
