package tch.service;

import java.util.List;

import tch.model.PaperDetail;

public interface IPaperDetailService {
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getPaperDetailById
	 * @Description: 根据主键查询记录
	 * @param id
	 * @return
	 * @return: PaperDetail
	 */
	public PaperDetail getPaperDetailById(String paperid);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getPaperDetailById
	 * @Description: 通过属性查询记录
	 * @param PaperDetail
	 * @return
	 * @return: PaperDetail
	 */
	public List<PaperDetail> getPaperDetailByAttr(PaperDetail paperDetail);
	
	/**
	 * 
	 * @user: tongchaohua
	 * @Title: getGeneralPaperDetailByAttr
	 * @Description: 通过五个属性进行模糊查询
	 * @param paperDetail
	 * @return
	 * @return: List<PaperDetail>
	 */
	public List<PaperDetail> getGeneralPaperDetailByAttr(PaperDetail paperDetail);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertPaperDetail
     * @Description: 插入记录（属性不允许为空）
     * @param PaperDetail
     * @return
     * @return: int
     */    
    public int insertPaperDetail(PaperDetail paperDetail);

    /**
     * 
     * @user: tongchaohua
     * @Title: insertPaperDetailSelective
     * @Description: 插入记录（除主键外。其他属性允许为空）
     * @param PaperDetail
     * @return
     * @return: int
     */
    public int insertPaperDetailSelective(PaperDetail paperDetail);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateByIdSelective
     * @Description: 更新记录（除主键外。其他属性允许为空）
     * @param PaperDetail
     * @return
     * @return: int
     */
    public int updateByIdSelective(PaperDetail paperDetail);

    /**
     * 
     * @user: tongchaohua
     * @Title: updateById
     * @Description: 更新记录（属性不允许为空）
     * @param PaperDetail
     * @return
     * @return: int
     */
    public int updateById(PaperDetail paperDetail);
    
    /**
     * 
     * @user: tongchaohua
     * @Title: deleteById
     * @Description: 删除记录
     * @param id
     * @return
     * @return: int
     */
    public int deleteById(String paperid);
}
