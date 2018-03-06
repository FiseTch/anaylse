package tch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tch.dao.SubjectMapper;
import tch.model.Subject;

@Service("subjectPaperService")
public class SubjectServiceImpl implements SubjectMapper {

	
	@Autowired
	private SubjectMapper subjectMapper;
	
	
	public SubjectMapper getSubjectMapper() {
		return subjectMapper;
	}

	public void setSubjectMapper(SubjectMapper subjectMapper) {
		this.subjectMapper = subjectMapper;
	}

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Subject record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Subject record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Subject selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Subject record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Subject record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
