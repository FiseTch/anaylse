package tch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tch.dao.DegreeStaticMapper;
import tch.model.DegreeStatic;

@Service("degreeStaticService")
public class DegreeStaticServiceImpl implements DegreeStaticMapper {

	
	@Autowired
	private DegreeStaticMapper degreeStaticMapper;
	
	
	public DegreeStaticMapper getDegreeStaticMapper() {
		return degreeStaticMapper;
	}

	public void setDegreeStaticMapper(DegreeStaticMapper degreeStaticMapper) {
		this.degreeStaticMapper = degreeStaticMapper;
	}

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(DegreeStatic record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(DegreeStatic record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public DegreeStatic selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(DegreeStatic record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(DegreeStatic record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
