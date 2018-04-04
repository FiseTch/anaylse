package tch.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import tch.dao.PaperMapper;
import tch.model.Paper;
import tch.service.IPaperService;


@Service("paperService")
public class PaperServiceImpl implements IPaperService {

	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource
	private PaperMapper paperMapper;

	public int deleteById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.deleteByPrimaryKey(id);
	}

	public int insertPaper(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.insert(paper);
	}

	public int insertPaperSelective(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.insertSelective(paper);
	}

	public Paper selectById(String id) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.selectByPrimaryKey(id);
	}

	public int updateByIdSelective(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.updateByPrimaryKeySelective(paper);
	}

	public int updateById(Paper paper) {
		log.info("执行"+Thread.currentThread().getStackTrace()[1].getMethodName());
		return paperMapper.updateByPrimaryKey(paper);
	}


}
