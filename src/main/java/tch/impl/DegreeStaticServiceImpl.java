package tch.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import tch.dao.DegreeStaticMapper;
import tch.model.DegreeStatic;
import tch.service.IDegreeStaticService;

@Service("degreeStaticService")
public class DegreeStaticServiceImpl implements IDegreeStaticService {

	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource
	private DegreeStaticMapper degreeStaticMapper;

	public int deleteById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.deleteByPrimaryKey(id);
	}

	public int insertDegreeStatic(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.insert(degreeStatic);
	}

	public int insertDegreeStaticSelective(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.insertSelective(degreeStatic);
	}

	public DegreeStatic selectById(Integer id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.selectByPrimaryKey(id);
	}

	public int updateByIdSelective(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.updateByPrimaryKeySelective(degreeStatic);
	}

	public int updateById(DegreeStatic degreeStatic) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return degreeStaticMapper.updateByPrimaryKey(degreeStatic);
	}
}
