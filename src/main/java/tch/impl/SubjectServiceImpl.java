package tch.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tch.dao.SubjectMapper;
import tch.model.Subject;
import tch.service.ISubjectService;
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
@Service("subjectService")
@Scope("prototype")
public class SubjectServiceImpl implements ISubjectService {

	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource
	private SubjectMapper subjectMapper;

	/*
	 * 通过主键id删除记录
	 */
	public int deleteById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入记录（属性不允许为空）
	 */
	public int insertSubject(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.insert(subject);
	}

	/**
	 * 插入记录（除主键外，其余的属性允许为空）
	 */
	public int insertSubjectSelective(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.insertSelective(subject);
	}

	/**
	 * 通过id查询记录
	 */
	public Subject getSubById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.getSubByPrimaryKey(id);
	}

	/**
	 * 根据主键id更新记录（其余属性允许为空）
	 */
	public int updateByIdSelective(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.updateByPrimaryKeySelective(subject);
	}

	/**
	 * 根据主键id更新记录（属性不允许为空）
	 */
	public int updateById(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.updateByPrimaryKey(subject);
	}

	/**
	 * 根据属性值查记录
	 */
	public Subject getSubByAttr(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.getSubByAttr(subject);
	}
	
	


}
