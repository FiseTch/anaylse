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

	public int deleteById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.deleteByPrimaryKey(id);
	}

	public int insertSubject(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.insert(subject);
	}

	public int insertSubjectSelective(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.insertSelective(subject);
	}

	public Subject selectById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.selectByPrimaryKey(id);
	}

	public int updateByIdSelective(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.updateByPrimaryKeySelective(subject);
	}

	public int updateById(Subject subject) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return subjectMapper.updateByPrimaryKey(subject);
	}
	
	


}
