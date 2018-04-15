package tch.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tch.dao.TeacherMapper;
import tch.model.Teacher;
import tch.service.ITeacherService;

/**
 * 
 * 
 * Copyright:
 * 
 * @class: tch.impl
 * @Description: 
 *
 * @version: v1.0.0
 * @author: tongch
 * @date: 2018-02-04
 * Modification History:
 * date         Author          Version            Description
 *------------------------------------------------------------
 * 2018-02-04     tongch          v1.1.0
 */

@Service("teacherService")
@Scope("prototype")
public class TeacherServiceImpl implements ITeacherService {
	
	private Log log = LogFactory.getLog(TeacherServiceImpl.class);
	
	@Resource
	private TeacherMapper teacherMapper;
	
	/**
	 * 通过主键id删除记录
	 */
	public int deleteById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.deleteByPrimaryKey(id);
	}
	/*
	 * 插入记录（属性不允许为空）
	 */
	public int insertTeacher(Teacher teacher) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.insert(teacher);
	}
	/**
	 * 插入记录（除主键外，其余的属性允许为空）
	 */
	public int insertTeacherSelective(Teacher teacher) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.insertSelective(teacher);
	}
	/**
	 * 通过id查询记录
	 */
	public Teacher getTeacById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.getTeacByPrimaryKey(id);
	}

	/**
	 * 根据主键id更新记录（其余属性允许为空）
	 */
	public int updateByIdSelective(Teacher teacher) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.updateByPrimaryKeySelective(teacher);
	}

	/**
	 * 根据主键id更新记录（属性不允许为空）
	 */
	public int updateById(Teacher teacher) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.updateByPrimaryKey(teacher);
	}
	/**
	 * 根据属性值查记录
	 */
	public Teacher getTeacByAttr(Teacher teacher) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.getTeacByAttr(teacher);
	}
	
	/**
	 * 查询所有的用户id
	 */
	public List<String> getAllTeacId() {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return teacherMapper.getAllTeacId();
	}

}
