package tch.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tch.controller.Validity;
import tch.dao.TeacherMapper;
import tch.model.Teacher;

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
public class TeacherServiceImpl implements TeacherMapper {
	private static Log log = LogFactory.getLog(Validity.class);
	
	private TeacherMapper teacherMapper;
	
	
	public TeacherMapper getTeacherMapper() {
		return teacherMapper;
	}

	public void setTeacherMapper(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Teacher record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Teacher record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Teacher selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Teacher record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Teacher record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
