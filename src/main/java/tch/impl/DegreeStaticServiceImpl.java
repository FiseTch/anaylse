package tch.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tch.dao.DegreeStaticMapper;
import tch.model.DegreeStatic;
import tch.service.IDegreeStaticService;

/**
 * 
 * 
 * Copyright:tch
 * 
 * @class: tch.impl
 * @Description: 
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-04-05
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-04-05     tongch          v1.1.0
 */
@Service("degreeStaticService")
@Scope("prototype")
public class DegreeStaticServiceImpl implements IDegreeStaticService {

	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource
	private DegreeStaticMapper degreeStaticMapper;

	/**
	 * 通过主键id删除记录
	 */
	public int deleteById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入记录（属性不允许为空）
	 */
	public int insertDegreeStatic(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.insert(degreeStatic);
	}

	/**
	 * 插入记录（除主键外，其余的属性允许为空）
	 */
	public int insertDegreeStaticSelective(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.insertSelective(degreeStatic);
	}

	/**
	 * 通过id查询记录
	 */
	public DegreeStatic getDegById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.getDegByPrimaryKey(id);
	}

	/**
	 * 根据主键id更新记录（其余属性允许为空）
	 */
	public int updateByIdSelective(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.updateByPrimaryKeySelective(degreeStatic);
	}

	/**
	 * 根据主键id更新记录（属性不允许为空）
	 */
	public int updateById(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.updateByPrimaryKey(degreeStatic);
	}

	/**
	 * 根据属性值查记录
	 */
	public DegreeStatic getDegByAttr(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.getDegByAttr(degreeStatic);
	}
}
