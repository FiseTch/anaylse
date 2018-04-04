package tch.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import tch.dao.SubjectMapper;
import tch.model.Subject;
import tch.service.ISubjectService;

@Service("subjectService")
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
